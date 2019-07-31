package by.talstaya.crackertracker.command;

public enum JspPath {
    INDEX("/index.jsp"),
    REGISTRATION("/jsp/registration.jsp"),
    TRANSLATE("/"),
    HOME("/jsp/home.jsp"),
    ERROR("/jsp/error.jsp"),
    SIGN_IN("/jsp/sign_in.jsp"),
    SETTINGS("/jsp/settings.jsp"),
    PRODUCT_LIST("/jsp/productList.jsp"),
    PRODUCT("/jsp/product.jsp"),
    DIET("/jsp/diet.jsp"),
    USER_LIST("/jsp/userList.jsp"),
    USER("/jsp/user.jsp"),
    SUPERVISOR("/jsp/supervisor.jsp"),
    SUPERVISOR_LIST("/jsp/supervisorList.jsp"),
    USER_LIST_OF_SUPERVISOR("/jsp/userListOfSupervisor.jsp"),
    REQUESTS_FOR_SUPERVISORS("/jsp/requestsForSupervisors.jsp");

    private String url;

    JspPath(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
