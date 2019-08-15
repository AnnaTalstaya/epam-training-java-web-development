<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>  <%--jstl tag--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head><title>Error Page</title></head>
<body>

<div style="float: left;">
    <img src="https://i.pinimg.com/originals/34/9a/dd/349add0e16d9b1d7531ade340766d402.png">
</div>
<div style="float: left;"><h1>Oops</h1>
    <c:choose>
        <c:when test="${pageContext.exception.message != null}">
            <h2>${pageContext.exception.message}</h2>
        </c:when>
        <c:otherwise>
            <c:if test="${error != null}">
                <h2>${error}</h2>
                <br/>
            </c:if>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${statusCode != null}">
            <h2>Status code: ${statusCode}</h2>
            <br/>
        </c:when>
        <c:otherwise>
            <h2>Status code: ${pageContext.errorData.statusCode}</h2>
            <br/>
        </c:otherwise>
    </c:choose>


    <h2>Return to the main page: <a href="${pageContext.servletContext.contextPath}">Main page</a></h2>
</div>


</body>
</html>