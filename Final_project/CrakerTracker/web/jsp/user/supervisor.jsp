<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>Title</title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/mdb.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stars.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.."
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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

<jsp:include page="../common/header.jsp"/>

<div id="page-container">
    <div id="content-wrap">
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <form id="supervisorFormId" method="post" action="show_supervisor">
                    <input type="hidden" name="command" value="show_supervisor">
                    <a class="nav-item nav-link active" data-toggle="tab" role="tab" aria-selected="true"
                       onclick="document.getElementById('supervisorFormId').submit();">
                        <fmt:message key="user.my_supervisor"/>
                    </a>
                </form>

                <form id="supervisorsFormId" method="post" action="supervisor_list">
                    <input type="hidden" name="command" value="supervisor_list">
                    <a class="nav-item nav-link" data-toggle="tab" role="tab" aria-selected="false"
                       onclick="document.getElementById('supervisorsFormId').submit();">
                        <fmt:message key="user.supervisors"/>
                    </a>
                </form>
            </div>
        </nav>


        <c:choose>
            <c:when test="${supervisor == null}">
                <p><fmt:message key="user.no_supervisor"/></p>
            </c:when>
            <c:otherwise>
                <div class="container">
                    <div class="card">
                        <div class="container-fliud">
                            <div class="wrapper row">

                                <div class="details col-md-6" style="padding-left: 80px;">
                                    <h3 class="product-title">${supervisor.firstName} ${supervisor.surname}</h3>

                                    <h5 class="sizes"><fmt:message key="registration.username"/>
                                        <span class="size" data-toggle="tooltip"
                                              title="small">${supervisor.username}</span>
                                    </h5>

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


                                    <!-- Rate the supervisor -->
                                    <h5><fmt:message key="supervisor.rate"/></h5>
                                    <div class="form-group">
                                        <div class="col-xs-12">

                                            <div id="reviewStars-input">
                                                <form method="post" action="rate_supervisor">
                                                    <input type="hidden" name="command" value="rate_supervisor">
                                                    <input type="hidden" name="supervisorId"
                                                           value="${supervisor.userId}"/>

                                                    <input id="star-4" type="radio" name="rating" value="5"
                                                           onclick="this.form.submit();"
                                                        ${rating==5 ? 'checked' : ''}
                                                           >
                                                    <label title="5" for="star-4"></label>

                                                    <input id="star-3" type="radio" name="rating" value="4"
                                                           onclick="this.form.submit();"
                                                        ${rating==4 ? 'checked' : ''}>
                                                    <label title="4" for="star-3"></label>

                                                    <input id="star-2" type="radio" name="rating" value="3"
                                                           onclick="this.form.submit();"
                                                        ${rating==3 ? 'checked' : ''}>
                                                    <label title="3" for="star-2"></label>

                                                    <input id="star-1" type="radio" name="rating" value="2"
                                                           onclick="this.form.submit();"
                                                        ${rating==2 ? 'checked' : ''}>
                                                    <label title="2" for="star-1"></label>

                                                    <input id="star-0" type="radio" name="rating" value="1"
                                                           onclick="this.form.submit();"
                                                        ${rating==1 ? 'checked' : ''}>
                                                    <label title="1" for="star-0"></label>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">

                                            <label>
                                                <form method="post" action="delete_supervisor">
                                                    <input type="hidden" name="command" value="delete_supervisor">

                                                    <button type="submit" class="btn btn-danger center-block">
                                                        <fmt:message key="user.delete"/>
                                                    </button>
                                                </form>
                                            </label>

                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <jsp:include page="../common/footer.jsp"/>

</div>

</body>
</html>
