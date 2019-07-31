<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="supervisor.requests"/></title>


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

<jsp:include page="./common/header.jsp"/>

<p>${message}</p>

<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist">

        <form id="usersFormId" method="post" action="user_list_of_supervisor">
            <input type="hidden" name="command" value="user_list_of_supervisor">
            <a class="nav-item nav-link" data-toggle="tab" role="tab" aria-selected="false"
               onclick="document.getElementById('usersFormId').submit();">
                <fmt:message key="supervisor.my_users"/>
            </a>
        </form>

        <form id="requestsFormId" method="post" action="show_requests_for_supervisor">
            <input type="hidden" name="command" value="show_requests_for_supervisor">
            <a class="nav-item nav-link active" data-toggle="tab" role="tab" aria-selected="true"
               onclick="document.getElementById('requestsFormId').submit();">
                <fmt:message key="supervisor.requests"/>
            </a>
        </form>

    </div>
</nav>


<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="registration.first_name"/></th>
        <th scope="col"><fmt:message key="registration.surname"/></th>
        <th scope="col"><fmt:message key="registration.username"/></th>
        <th scope="col"></th> <!-- Accept-->
        <th scope="col"></th> <!-- Reject-->
    </tr>
    </thead>
    <tbody>

    <c:forEach begin="${startIndexOfRequestList}" end="${startIndexOfRequestList + requestsPerPage - 1}" var="user"
               items="${requestListForSupervisor}">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.surname}</td>
            <td>${user.username}</td>

            <!--Accept -->
            <td>
                <form method="post" action="supervisor_accepts_request">
                    <input type="hidden" name="command" value="supervisor_accepts_request">
                    <input type="hidden" name="userId" value="${user.userId}">

                    <button type="submit" class="btn btn-success center-block">
                        <fmt:message key="supervisor.accept"/>
                    </button>
                </form>
            </td>

            <!--Reject-->
            <td>
                <form method="post" action="supervisor_rejects_request">
                    <input type="hidden" name="command" value="supervisor_rejects_request">
                    <input type="hidden" name="userId" value="${user.userId}">

                    <button type="submit" class="btn btn-danger center-block">
                        <fmt:message key="supervisor.reject"/>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<ctg:pagination startIndexOfObjectList="${startIndexOfRequestList}"
                objectsPerPage="${requestsPerPage}"
                indexOfPage="${indexOfPage}"
                numberOfObjects="${requestListForSupervisor.size()}"
                locale="${locale}"
                commandValue="show_requests_for_supervisor"/>

<jsp:include page="./common/footer.jsp"/>


</body>
</html>
