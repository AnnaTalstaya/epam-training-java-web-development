package by.talstaya.crackertracker.command;

public enum JspPath {
    INDEX("/index.jsp"),
    REGISTRATION("/jsp/registration.jsp"),
    HOME("/jsp/common/home.jsp"),
    ERROR("/jsp/error.jsp"),
    SIGN_IN("/jsp/sign_in.jsp"),
    SETTINGS("/jsp/user/settings.jsp"),
    PRODUCT_LIST("/jsp/common/productList.jsp"),
    PRODUCT("/jsp/common/product.jsp"),
    DIET("/jsp/user/diet.jsp"),
    USER_DIET_FOR_SUPERVISOR("/jsp/supervisor/userDietForSupervisor.jsp"),
    USER_LIST("/jsp/administrator/userList.jsp"),
    USER("/jsp/administrator/user.jsp"),
    SUPERVISOR("/jsp/user/supervisor.jsp"),
    SUPERVISOR_LIST("/jsp/user/supervisorList.jsp"),
    USER_LIST_OF_SUPERVISOR("/jsp/supervisor/userListOfSupervisor.jsp"),
    REQUESTS_FOR_SUPERVISOR("/jsp/supervisor/requestsForSupervisor.jsp");

    private String url;

    JspPath(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
