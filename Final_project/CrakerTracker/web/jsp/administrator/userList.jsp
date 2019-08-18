<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="users.users"/></title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/mdb.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/settings.js"></script>


    <script src="${pageContext.request.contextPath}/css/registration.css"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <!-- Bootstrap -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/mdb.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-social.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<jsp:include page="../common/header.jsp"/>

<div id="page-container">
    <div id="content-wrap">

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="registration.username"/></th>
                <th scope="col"><fmt:message key="user.type"/></th>
                <th scope="col"></th> <!--Details-->
                <th scope="col"></th> <!-- Remove-->
            </tr>
            </thead>
            <tbody>

            <c:if test="${userList.size() > 0}">
                <c:forEach begin="${startIndexOfUserList}" end="${startIndexOfUserList + usersPerPage - 1}" var="user"
                           items="${userList}">
                    <tr>
                        <td>${user.username}</td>

                        <td>
                            <c:choose>
                                <c:when test="${not greaterThanOneAdmin and user.userType.name() == 'ADMINISTRATOR'}">
                                    <label>
                                        <select class="custom-select" name="userType" onchange="this.form.submit()" required
                                                disabled>
                                            <option ${user.userType.name()=="USER"?"selected":""} value="USER"><fmt:message
                                                    key="user.type.user"/>
                                            </option>
                                            <!-- value отправляется на сервер-->
                                            <option ${user.userType.name()=="ADMINISTRATOR"?"selected":""}
                                                    value="ADMINISTRATOR">
                                                <fmt:message
                                                        key="user.type.administrator"/>
                                            </option>
                                            <option ${user.userType.name()=="SUPERVISOR"?"selected":""} value="SUPERVISOR">
                                                <fmt:message
                                                        key="user.type.supervisor"/>
                                            </option>
                                        </select>
                                    </label>
                                </c:when>
                                <c:otherwise>
                                    <form method="post" action="change_user_type" class="form-horizontal">
                                        <input type="hidden" name="command" value="change_user_type">
                                        <input type="hidden" name="userId" value="${user.userId}">

                                        <label>
                                            <select class="custom-select" name="userType" onchange="this.form.submit()"
                                                    required>
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
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <form method="get" action="show_user_details">
                                <input type="hidden" name="userId" value="${user.userId}">

                                <button type="submit" class="btn btn-primary center-block"><fmt:message
                                        key="product.details"/></button>
                            </form>
                        </td>
                        <!--Delete -->
                        <td>
                            <c:choose>
                                <c:when test="${not greaterThanOneAdmin and user.userType.name() == 'ADMINISTRATOR'}">

                                    <button type="submit" class="btn btn-danger center-block" disabled><fmt:message
                                            key="user.delete"/></button>
                                </c:when>
                                <c:otherwise>
                                    <form method="post" action="delete_user">
                                        <input type="hidden" name="command" value="delete_user">
                                        <input type="hidden" name="userId" value="${user.userId}">

                                        <button type="submit" class="btn btn-danger center-block"><fmt:message
                                                key="user.delete"/></button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>


            </tbody>
        </table>

        <ctg:pagination startIndexOfObjectList="${startIndexOfUserList}"
                        objectsPerPage="${usersPerPage}"
                        indexOfPage="${indexOfPage}"
                        numberOfObjects="${userList.size()}"
                        locale="${locale}"
                        commandValue="user_list"/>

    </div>
    <jsp:include page="../common/footer.jsp"/>

</div>


</body>
</html>
