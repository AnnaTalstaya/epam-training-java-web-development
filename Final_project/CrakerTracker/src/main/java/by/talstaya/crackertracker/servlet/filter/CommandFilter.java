package by.talstaya.crackertracker.servlet.filter;

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
        commands.put("/registration", "visit_registration");
        commands.put("/sign_in", "visit_sign_in");
        commands.put("/settings", "visit_settings");
        commands.put("/search", "search");
        commands.put("/product_list", "product_list");
        commands.put("/visit_product_list", "visit_product_list");
        commands.put("/translate", "translate");
        commands.put("/show_supervisor", "show_supervisor");
        commands.put("/supervisor_list", "supervisor_list");
        commands.put("/change_user_type", "change_user_type");
        commands.put("/delete_user", "delete_user");
        commands.put("/user_list", "user_list");
        commands.put("/show_user_details", "show_user_details");
        commands.put("/user_list_of_supervisor", "user_list_of_supervisor");
        commands.put("/show_requests_for_supervisor", "show_requests_for_supervisor");
        commands.put("/diet", "diet");
        commands.put("/show_diet", "show_diet");
        commands.put("/show_product_details", "show_product_details");
        commands.put("/user_diet_for_supervisor", "user_diet_for_supervisor");
        commands.put("/show_user_diet_for_supervisor", "show_user_diet_for_supervisor");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (request instanceof HttpServletRequest) {

            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();

            int beginOfCommand = contextPath.length();
            String commandInURI = uri.substring(beginOfCommand);
            String command = commands.get(commandInURI);

            httpRequest.setAttribute(COMMAND, command);

            chain.doFilter(request, response);
        } else {
            LOGGER.error("You are trying to use HTTP filter for not HTTP request");
        }
    }

    public void destroy() {
    }

}

