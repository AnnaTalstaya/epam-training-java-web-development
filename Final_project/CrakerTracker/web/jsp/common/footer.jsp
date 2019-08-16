<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <%--jstl tag--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>  <%--set a resource bundle--%>

<!-- Footer -->
<footer id="footer" class="page-footer font-small bg-success darken-3">

    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">
        <fmt:message key="footer.copyright"/>
    </div>
    <!-- Copyright -->

</footer>
<!-- Footer -->