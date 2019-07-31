package by.talstaya.crackertracker.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = { "/*" })
public class CommandFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String COMMAND = "command";

    private static Map<String, String> commands = new HashMap<>();

    static {
        commands.put("/registration", "registration");
        commands.put("/sign_in", "sign_in");
        commands.put("/sign_out", "sign_out");
        commands.put("/settings", "settings");
        commands.put("/search", "search");
        commands.put("/product_list", "product_list");
        commands.put("/translate", "translate");
        commands.put("/show_supervisor", "show_supervisor");
        commands.put("/supervisor_list", "supervisor_list");
        commands.put("/rate_supervisor", "rate_supervisor");
        commands.put("/delete_supervisor", "delete_supervisor");
        commands.put("/send_request_for_supervisor", "send_request_for_supervisor");
        commands.put("/delete_request_for_supervisor", "delete_request_for_supervisor");
        commands.put("/change_user_type", "change_user_type");
        commands.put("/delete_user", "delete_user");
        commands.put("/user_list", "user_list");
        commands.put("/show_user_details", "show_user_details");
        commands.put("/user_list_of_supervisor", "user_list_of_supervisor");
        commands.put("/show_requests_for_supervisor", "show_requests_for_supervisor");
        commands.put("/supervisor_accepts_request", "supervisor_accepts_request");
        commands.put("/supervisor_rejects_request", "supervisor_rejects_request");
        commands.put("/delete_user_of_supervisor", "delete_user_of_supervisor");
        commands.put("/diet", "diet");
        commands.put("/show_diet", "show_diet");
        commands.put("/update_quantity_in_diet", "update_quantity_in_diet");
        commands.put("/delete_meal", "delete_meal");
        commands.put("/delete_comment", "delete_comment");
        commands.put("/comment", "comment");
        commands.put("/add_meal", "add_meal");
        commands.put("/show_product_details", "show_product_details");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (request instanceof HttpServletRequest) {

            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();

            int beginAction = contextPath.length();
            String actionName = uri.substring(beginAction);
            String actionClass = commands.get(actionName);

            httpRequest.setAttribute(COMMAND, actionClass);

            chain.doFilter(request, response);
        } else {
            LOGGER.error("You are trying to use HTTP filter for not HTTP request");
        }
    }

    public void destroy() {
    }

}

