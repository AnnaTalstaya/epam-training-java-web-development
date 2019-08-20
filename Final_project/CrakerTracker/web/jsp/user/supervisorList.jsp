<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="user.supervisors"/></title>

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
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <form id="supervisorFormId" method="post" action="show_supervisor">
                    <input type="hidden" name="command" value="show_supervisor">
                    <a class="nav-item nav-link" data-toggle="tab" role="tab" aria-selected="false"
                       onclick="document.getElementById('supervisorFormId').submit();">
                        <fmt:message key="user.my_supervisor"/>
                    </a>
                </form>
                <form id="supervisorsFormId" method="post" action="supervisor_list">
                    <input type="hidden" name="command" value="supervisor_list">
                    <a class="nav-item nav-link active" data-toggle="tab" role="tab" aria-selected="true"
                       onclick="document.getElementById('supervisorsFormId').submit();">
                        <fmt:message key="user.supervisors"/>
                    </a>
                </form>
            </div>
        </nav>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="registration.first_name"/></th>
                <th scope="col"><fmt:message key="registration.surname"/></th>
                <th scope="col"><fmt:message key="supervisor.rating"/></th>
                <th scope="col"></th> <!--Employment-->
                <th scope="col"></th> <!--Delete request-->
            </tr>
            </thead>
            <tbody>

            <c:if test="${supervisorList.size() > 0}">
                <c:forEach begin="${startIndexOfSupervisorList}"
                           end="${startIndexOfSupervisorList + supervisorsPerPage - 1}"
                           var="supervisor"
                           items="${supervisorList}">
                    <tr>
                        <td>${supervisor.firstName}</td>
                        <td>${supervisor.surname}</td>
                        <td>
                            <div class="col-xs-12 col-md-6 text-center">
                                <h1 class="rating-num">${supervisor.rating}</h1>
                                <c:if test="${supervisor.rating < 0.5}">
                                    <span class="fa fa-star" id="star1"></span>
                                    <span class="fa fa-star" id="star2"></span>
                                    <span class="fa fa-star" id="star3"></span>
                                    <span class="fa fa-star" id="star4"></span>
                                    <span class="fa fa-star" id="star5"></span>
                                </c:if>
                                <c:if test="${supervisor.rating >= 0.5 and supervisor.rating < 1.5}">
                                    <span class="fa fa-star" id="star1" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star2"></span>
                                    <span class="fa fa-star" id="star3"></span>
                                    <span class="fa fa-star" id="star4"></span>
                                    <span class="fa fa-star" id="star5"></span>
                                </c:if>
                                <c:if test="${supervisor.rating >= 1.5  and supervisor.rating < 2.5}">
                                    <span class="fa fa-star" id="star1" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star2" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star3"></span>
                                    <span class="fa fa-star" id="star4"></span>
                                    <span class="fa fa-star" id="star5"></span>
                                </c:if>
                                <c:if test="${supervisor.rating >= 2.5  and supervisor.rating < 3.5}">
                                    <span class="fa fa-star" id="star1" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star2" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star3" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star4"></span>
                                    <span class="fa fa-star" id="star5"></span>
                                </c:if>
                                <c:if test="${supervisor.rating >= 3.5  and supervisor.rating < 4.5}">
                                    <span class="fa fa-star" id="star1" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star2" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star3" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star4" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star5"></span>
                                </c:if>
                                <c:if test="${supervisor.rating >= 4.5}">
                                    <span class="fa fa-star" id="star1" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star2" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star3" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star4" style="color:#fff108"></span>
                                    <span class="fa fa-star" id="star5" style="color:#fff108"></span>
                                </c:if>
                            </div>

                        </td>

                        <!--Employ -->
                        <td>
                            <c:choose>
                                <c:when test="${containsSupervisorOrRequestForSupervisor or User.userId==supervisor.userId}">
                                    <button type="submit" class="btn btn-primary center-block" disabled><fmt:message
                                            key="supervisor.employ"/></button>
                                </c:when>
                                <c:otherwise>
                                    <form method="post" action="send_request_for_supervisor">
                                        <input type="hidden" name="command" value="send_request_for_supervisor">
                                        <input type="hidden" name="supervisorId" value="${supervisor.userId}">

                                        <button type="submit" class="btn btn-primary center-block"><fmt:message
                                                key="supervisor.employ"/></button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <!--Delete request-->
                        <td>
                            <c:if test="${requestedSupervisorId==supervisor.userId}">
                                <form method="post" action="delete_request_for_supervisor">
                                    <input type="hidden" name="command" value="delete_request_for_supervisor">

                                    <button type="submit" class="btn btn-danger center-block">
                                        <fmt:message key="user.delete_request"/>
                                    </button>
                                </form>
                            </c:if>
                        </td>


                    </tr>
                </c:forEach>
            </c:if>

            </tbody>
        </table>

        <ctg:pagination objectsPerPage="${supervisorsPerPage}"
                        indexOfPage="${indexOfPage}"
                        numberOfObjects="${supervisorListSize}"
                        locale="${locale}"
                        commandValue="supervisor_list"/>

    </div>
    <jsp:include page="../common/footer.jsp"/>

</div>


</body>
</html>
