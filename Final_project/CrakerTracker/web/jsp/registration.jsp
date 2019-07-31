<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="header.sign_in.registration"/></title>

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/mdb.min.js"></script>

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

<jsp:include page="./common/header.jsp"/>

<div class="container">
    <div style="padding-top:20px"></div>

    <form method="post" action="registration" class="form-horizontal" role="form">
        <input type="hidden" name="command" value="registration">
        <input type="hidden" name="page_is_activated" value="true">

        <h2><fmt:message key="registration.registration"/></h2>
        <div class="form-group">
            <label for="firstName" class="col-sm-7 control-label"><fmt:message key="registration.first_name"/>* </label>
            <div class="col-sm-11">
                <input type="text" name="firstName" value="${firstName}" maxlength="50" id="firstName"
                       placeholder="<fmt:message key="registration.first_name"/> "
                       class="form-control" required autofocus>
                <span style="color:red;">${errorFirstName}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="lastName" class="col-sm-7 control-label"><fmt:message key="registration.surname"/>* </label>
            <div class="col-sm-11">
                <input type="text" name="surname" value="${surname}" maxlength="50" id="lastName"
                       placeholder="<fmt:message key="registration.surname"/> " required
                       class="form-control">
                <span style="color:red;">${errorSurname}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-7 control-label"><fmt:message key="registration.email"/>* </label>
            <div class="col-sm-11">
                <input type="email" name="email" value="${email}" maxlength="50" id="email"
                       placeholder="<fmt:message key="registration.email"/>" class="form-control"
                       required>
                <span style="color:red;">${errorEmail}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="username" class="col-sm-7 control-label"><fmt:message key="registration.username"/>*</label>
            <div class="col-sm-11">
                <input type="text" name="username" value="${username}" minlength="4" maxlength="50" id="username"
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
                <input type="date" name="dateOfBirth" value="${dateOfBirth}" maxlength="20" id="dateOfBirth"
                       class="form-control">
            </div>
        </div>

        <div class="form-group">
            <label for="Height" class="col-sm-7 control-label"><fmt:message key="registration.height"/> </label>
            <div class="col-sm-11">
                <input name="height" value="${height}" maxlength="4" id="height"
                       placeholder="<fmt:message key="registration.height_message"/>"
                       class="form-control">
                <span style="color:red;">${errorHeight}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="weight" class="col-sm-7 control-label"><fmt:message key="registration.weight"/></label>
            <div class="col-sm-11">
                <input name="weight" value="${weight}" maxlength="4" id="weight"
                       placeholder="<fmt:message key="registration.weight_message"/> "
                       class="form-control">
                <span style="color:red;">${errorWeight}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-7 control-label"><fmt:message key="registration.password"/>*</label>
            <div class="col-sm-11">
                <input type="password" name="password" minlength="7" maxlength="16"
                       id="password"
                       placeholder="<fmt:message key="registration.password"/>" class="form-control"
                       required>
                <small class="form-text text-muted">
                    <fmt:message key="registration.password.title"/>
                </small>
                <span style="color:red;">${errorPassword}</span>
                <span style="color:red;">${errorPassAndConfirmedPassMessage}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="confirmedPassword" class="col-sm-7 control-label"><fmt:message
                    key="registration.confirm_password"/>*</label>
            <div class="col-sm-11">
                <input type="password" name="confirmedPassword" minlength="7" maxlength="16"
                       id="confirmedPassword"
                       placeholder="<fmt:message key="registration.password"/>"
                       class="form-control" required>
                <span style="color:red;">${errorPassword}</span>
                <span style="color: red">${errorPassAndConfirmedPassMessage}</span>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-11 col-sm-offset-3">
                <span class="help-block">*<fmt:message key="registration.required_fields"/></span>
            </div>
        </div>
        <button type="submit" class="btn btn-primary btn-block"><fmt:message
                key="registration.button.register"/></button>
    </form> <!-- /form -->

    <div style="padding-bottom:20px"></div>

</div> <!-- ./container -->


<jsp:include page="./common/footer.jsp"/>


</body>
</html>
