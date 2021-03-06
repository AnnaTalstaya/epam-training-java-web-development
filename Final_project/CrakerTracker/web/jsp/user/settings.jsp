<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="settings.settings"/></title>

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

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          id="bootstrap-css">
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">

</head>
<body>

<jsp:include page="../common/header.jsp"/>

<div id="page-container">
    <div id="content-wrap">
        <div class="container">
            <div style="padding-top:20px"></div>

            <form action="settings" method="post" class="form-horizontal" role="form">
                <input type="hidden" name="command" value="settings">

                <h2><fmt:message key="header.profile.settings"/></h2>
                <div class="form-group">
                    <label for="firstName" class="col-sm-7 control-label">
                        <fmt:message key="registration.first_name"/>
                    </label>
                    <div class="col-sm-11">
                        <input type="text" name="firstName" value="${User.firstName}" maxlength="50"
                               id="firstName"
                               placeholder="<fmt:message key="registration.first_name"/> "
                               class="form-control" required autofocus>
                        <span style="color:red;">${errorFirstName}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastName" class="col-sm-7 control-label"><fmt:message
                            key="registration.surname"/></label>
                    <div class="col-sm-11">
                        <input type="text" name="surname" value="${User.surname}" maxlength="50" id="lastName"
                               placeholder="<fmt:message key="registration.surname"/> " required
                               class="form-control">
                        <span style="color:red;">${errorSurname}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="col-sm-7 control-label"><fmt:message
                            key="registration.email"/> </label>
                    <div class="col-sm-11">
                        <input type="email" name="email" value="${User.email}"
                               maxlength="50"
                               id="email"
                               placeholder="<fmt:message key="registration.email"/>" class="form-control"
                               required>
                        <span style="color:red;">${errorEmail}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="username" class="col-sm-7 control-label"><fmt:message
                            key="registration.username"/></label>
                    <div class="col-sm-11">
                        <input type="text" name="username" value="${User.username}"
                               pattern="^[\w][.\w]{2,48}[\w]$" minlength="4" maxlength="50"
                               id="username"
                               placeholder="<fmt:message key="registration.username"/>"
                               class="form-control" required>
                        <small class="form-text text-muted">
                            <fmt:message key="registration.username.title"/>
                        </small>
                        <span style="color:red;">${errorUsername}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dateOfBirth" class="col-sm-7 control-label"><fmt:message
                            key="registration.date_of_birth"/> </label>
                    <div class="col-sm-11">
                        <input type="date" name="dateOfBirth" value="${User.dateOfBirth}" maxlength="20"
                               id="dateOfBirth"
                               class="form-control">
                    </div>
                </div>


                <div class="form-group">
                    <label for="height" class="col-sm-7 control-label"><fmt:message
                            key="registration.height"/> </label>
                    <div class="col-sm-11">
                        <c:choose>
                            <c:when test="${User.height != 0}">
                                <input name="height" value="${User.height}"
                                       pattern="^[1-9]\d{1,2}(\.[\d])?$" maxlength="5" min="0"
                                       id="height"
                                       placeholder="<fmt:message key="registration.height_message"/>"
                                       class="form-control">
                            </c:when>
                            <c:otherwise>
                                <input name="height"
                                       pattern="^[1-9]\d{1,2}(\.[\d])?$" maxlength="5" min="0"
                                       id="height"
                                       placeholder="<fmt:message key="registration.height_message"/>"
                                       class="form-control">
                            </c:otherwise>
                        </c:choose>

                        <small class="form-text text-muted">
                            <fmt:message key="registration.height.title"/>
                        </small>
                        <span style="color:red;">${errorHeight}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="weight" class="col-sm-7 control-label"><fmt:message
                            key="registration.weight"/></label>
                    <div class="col-sm-11">
                        <c:choose>
                            <c:when test="${User.weight != 0}">
                                <input name="weight" value="${User.weight}"
                                       pattern="^[1-9]\d{1,2}(\.[\d])?$" maxlength="5" min="0"
                                       id="weight"
                                       placeholder="<fmt:message key="registration.weight_message"/> "
                                       class="form-control">
                            </c:when>
                            <c:otherwise>
                                <input name="weight"
                                       pattern="^[1-9]\d{1,2}(\.[\d])?$" maxlength="5" min="0"
                                       id="weight"
                                       placeholder="<fmt:message key="registration.weight_message"/> "
                                       class="form-control">
                            </c:otherwise>
                        </c:choose>

                        <small class="form-text text-muted">
                            <fmt:message key="registration.weight.title"/>
                        </small>
                        <span style="color:red;">${errorWeight}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-sm-7 control-label"><fmt:message
                            key="registration.password"/></label>
                    <div class="col-sm-11">
                        <input type="password" name="currentPassword"
                               pattern="(?=.*[\d])(?=.*[a-z])(?=.*[A-Z]).{7,16}" minlength="7" maxlength="16"
                               id="password"
                               placeholder="<fmt:message key="settings.current_password"/>"
                               class="form-control">
                        <small class="form-text text-muted">
                            <fmt:message key="registration.password.title"/>
                        </small>
                        <span style="color:red;">${errorPassword}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="new_password" class="col-sm-7 control-label"><fmt:message
                            key="settings.new_password"/></label>
                    <div class="col-sm-11">
                        <input type="password" name="newPassword"
                               pattern="(?=.*[\d])(?=.*[a-z])(?=.*[A-Z]).{7,16}" minlength="7" maxlength="16"
                               id="new_password"
                               placeholder="<fmt:message key="settings.new_password"/>" class="form-control">
                        <span style="color:red;">${errorPassword}</span>
                        <span style="color:red;">${errorPassAndConfirmedPassMessage}</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="confirm_new_password" class="col-sm-7 control-label"><fmt:message
                            key="settings.confirm_new_password"/></label>
                    <div class="col-sm-11">
                        <input type="password" name="confirmedNewPassword"
                               pattern="(?=.*[\d])(?=.*[a-z])(?=.*[A-Z]).{7,16}" minlength="7" maxlength="16"
                               id="confirm_new_password"
                               placeholder="<fmt:message key="settings.confirm_new_password"/>"
                               class="form-control">
                        <span style="color:red;">${errorPassword}</span>
                        <span style="color:red;">${errorPassAndConfirmedPassMessage}</span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <br>
                        <button class="btn btn-lg btn-success" type="submit"><i
                                class="glyphicon glyphicon-ok-sign"></i>
                            <fmt:message key="settings.button.save"/>
                        </button>
                        <span style="color:red;">${errorData}</span>
                        <span style="color:green;">${ok}</span>
                    </div>
                </div>
            </form>

            <div style="padding-bottom:20px"></div>

        </div> <!-- ./container -->
    </div>
    <jsp:include page="../common/footer.jsp"/>

</div>


</body>
</html>
