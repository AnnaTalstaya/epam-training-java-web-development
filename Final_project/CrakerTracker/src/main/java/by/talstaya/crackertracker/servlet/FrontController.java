package by.talstaya.crackertracker.servlet;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.CommandFactory;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.exception.PoolConnectionException;
import by.talstaya.crackertracker.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FrontController is used to retrieve command from request, correlate the command with the corresponding class
 * and execute this command
 *
 * @author Anna Talstaya
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/registration", "/sign_in", "/sign_out", "/settings", "/search",
        "/visit_product_list", "/product_list", "/translate",
        "/show_supervisor", "/supervisor_list", "/rate_supervisor", "/delete_supervisor",
        "/send_request_for_supervisor", "/delete_request_for_supervisor",
        "/change_user_type", "/delete_user", "/user_list", "/show_user_details",
        "/user_list_of_supervisor", "/show_requests_for_supervisor", "/supervisor_accepts_request", "/supervisor_rejects_request",
        "/delete_user_of_supervisor",
        "/diet", "/show_diet", "/update_quantity_in_diet", "/delete_meal", "/delete_comment", "/comment",
        "/add_meal", "/show_product_details", "/user_diet_for_supervisor", "/show_user_diet_for_supervisor"})

public class FrontController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String COMMAND = "command";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String RESPONSE = "response";

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page;

        CommandFactory commandFactory = CommandFactory.getInstance();
        try {
            if (request.getParameter(COMMAND) != null) {
                LOGGER.info("Request. Parameter = " + request.getParameter(COMMAND));
                Command command = commandFactory.receiveCommand(request.getParameter(COMMAND));
                page = command.execute(request, response);

            } else if (request.getAttribute(COMMAND) != null) {
                LOGGER.info("Request through filter. Attribute = " + request.getAttribute(COMMAND));
                Command command = commandFactory.receiveCommand((String) request.getAttribute(COMMAND));
                page = command.execute(request, response);

            } else {
                LOGGER.error("Command not received");
                request.setAttribute(ERROR, "Command not received");
                request.setAttribute(STATUS_CODE, 404);
                page = JspPath.ERROR.getUrl();
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(ERROR, e.getMessage());
            request.setAttribute(STATUS_CODE, 500);
            page = JspPath.ERROR.getUrl();
        }

        if (page != null) {
            if (request.getAttribute(RESPONSE) != null && (boolean) request.getAttribute(RESPONSE)) {
                response.sendRedirect(page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        }

    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().closePool();
        } catch (PoolConnectionException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
