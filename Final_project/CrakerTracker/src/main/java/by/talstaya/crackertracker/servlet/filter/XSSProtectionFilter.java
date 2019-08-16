package by.talstaya.crackertracker.servlet.filter;

import by.talstaya.crackertracker.command.JspPath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;

/**
 * XSSProtectionFilter is used to protect the site from cross site scripting(xss)
 *
 * @author Anna Talstaya
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/*"})
public class XSSProtectionFilter implements Filter {

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    private static final String JS_SCRIPT_BEGIN_TAG = "<script>";
    private static final String JS_SCRIPT_END_TAG = "</script>";

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String text = request.getParameter(parameterNames.nextElement()).toLowerCase();
            if(text.contains(JS_SCRIPT_BEGIN_TAG) || text.contains(JS_SCRIPT_END_TAG)){
                request.setAttribute(ERROR, "Brrr, XSS attack!!!");
                request.setAttribute(STATUS_CODE, 404);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher(JspPath.ERROR.getUrl());
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
