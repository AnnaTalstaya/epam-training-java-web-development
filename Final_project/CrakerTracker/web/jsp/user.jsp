<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>${user.firstName} ${user.surname}</title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/mdb.min.js"></script>

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">

    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/mdb.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-social.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
</head>
<body>

<jsp:include page="./common/header.jsp"/>

<div id="page-container">
    <div id="content-wrap">
        <div class="container">
            <div class="card">
                <div class="container-fliud">
                    <div class="wrapper row">
                        <%--<div class="preview col-md-4">--%>

                        <%--<div class="preview-pic tab-content">--%>
                        <%--<div class="tab-pane active" id="pic-1"><img--%>
                        <%--src="${pageContext.request.contextPath}/images/products/${product.imageURL}" width="200"--%>
                        <%--height="250"/></div>--%>
                        <%--</div>--%>

                        <%--</div>--%>
                        <div class="details col-md-6" style="padding-left: 80px;">
                            <h3 class="product-title">${user.firstName} ${user.surname}</h3>

                            <h5 class="sizes"><fmt:message key="registration.email"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.email}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="registration.username"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.username}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="registration.date_of_birth"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.dateOfBirth}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="registration.weight"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.weight}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="registration.height"/>
                                <span class="size" data-toggle="tooltip" title="small">${user.height}</span>
                            </h5>

                            <c:if test="${user.userType.name() == 'SUPERVISOR'}">
                                <h5 class="sizes"><fmt:message key="user.rating"/>
                                    <span class="size" data-toggle="tooltip" title="small">${user.rating}</span>
                                </h5>
                            </c:if>

                            <div class="form-group">
                                <div class="col-xs-12">

                                    <c:choose>
                                        <c:when test="${not greaterThanOneAdmin and user.userType.name() == 'ADMINISTRATOR'}">
                                            <label>
                                                <select class="custom-select" name="userType"
                                                        onchange="this.form.submit()"
                                                        required
                                                        disabled>
                                                    <option ${user.userType.name()=="USER"?"selected":""} value="USER">
                                                        <fmt:message
                                                                key="user.type.user"/>
                                                    </option>
                                                    <!-- value отправляется на сервер-->
                                                    <option ${user.userType.name()=="ADMINISTRATOR"?"selected":""}
                                                            value="ADMINISTRATOR">
                                                        <fmt:message
                                                                key="user.type.administrator"/>
                                                    </option>
                                                    <option ${user.userType.name()=="SUPERVISOR"?"selected":""}
                                                            value="SUPERVISOR">
                                                        <fmt:message
                                                                key="user.type.supervisor"/>
                                                    </option>
                                                </select>
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>
                                                <form method="post" action="change_user_type" class="form-horizontal">
                                                    <input type="hidden" name="command" value="change_user_type">
                                                    <input type="hidden" name="userId" value="${user.userId}">
                                                    <input type="hidden" name="userDetails" value="true">

                                                    <label>
                                                        <select class="custom-select" name="userType"
                                                                onchange="this.form.submit()" required>
                                                            <option ${user.userType.name()=="USER"?"selected":""}
                                                                    value="USER">
                                                                <fmt:message
                                                                        key="user.type.user"/>
                                                            </option>
                                                            <!-- value отправляется на сервер-->
                                                            <option ${user.userType.name()=="ADMINISTRATOR"?"selected":""}
                                                                    value="ADMINISTRATOR">
                                                                <fmt:message
                                                                        key="user.type.administrator"/>
                                                            </option>
                                                            <option ${user.userType.name()=="SUPERVISOR"?"selected":""}
                                                                    value="SUPERVISOR">
                                                                <fmt:message
                                                                        key="user.type.supervisor"/>
                                                            </option>
                                                        </select>
                                                    </label>

                                                    <c:if test="${ok != null}">
                                                        <p style="color:green;">${ok}</p>
                                                    </c:if>
                                                </form>
                                            </label>

                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${not greaterThanOneAdmin and user.userType.name() == 'ADMINISTRATOR'}">

                                            <button type="submit" class="btn btn-danger center-block" disabled>
                                                <fmt:message
                                                        key="user.delete"/></button>
                                        </c:when>
                                        <c:otherwise>
                                            <label>
                                                <form method="post" action="delete_user">
                                                    <input type="hidden" name="command" value="delete_user">
                                                    <input type="hidden" name="userId" value="${user.userId}">

                                                    <button type="submit" class="btn btn-danger center-block">
                                                        <fmt:message
                                                                key="user.delete"/></button>
                                                </form>
                                            </label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="./common/footer.jsp"/>

</div>


</body>
</html>
