<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/jsp/error.jsp" %>

<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<!--Fontawesome CDN-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
      integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<!--Navbar-->
<nav class="navbar navbar-expand-lg navbar-dark bg-success header">

    <!-- Navbar brand -->
    <a class="navbar-brand" href="${pageContext.servletContext.contextPath}">
        <img style="width:120px; height:50px;" alt="Cracker Tracker"
             src="${pageContext.servletContext.contextPath}/images/logo.png"/>
    </a>

    <!-- Collapse button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
            aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Collapsible content -->
    <div class="collapse navbar-collapse" id="basicExampleNav">

        <!--SearchCommand-->
        <form method="get" class="form-inline" action="search">
            <input name="nameOrWordInName" value="${nameOrWordInName}"
                   maxlength="100" pattern="^.{0,100}$"
                   class="form-control mr-sm-2" type="text" placeholder="<fmt:message key="header.search_text"/>">
            <button type="submit" class="btn btn-primary"><fmt:message key="header.search_button"/></button>
        </form>

        <!-- Links -->
        <ul class="navbar-nav my-2 my-lg-0 ml-auto">

            <!-- Products -->
            <li class="nav-item">
                <form id="productsForm" method="post" action="visit_product_list">
                    <input type="hidden" name="command" value="visit_product_list">
                    <a class="nav-link" onclick="document.getElementById('productsForm').submit();">
                        <fmt:message key="header.products"/>
                    </a>
                </form>
            </li>

            <!--Supervisors for users-->
            <c:if test="${User != null}">
                <li class="nav-item">
                    <form id="supervisorFormId" method="post" action="show_supervisor">
                        <input type="hidden" name="command" value="show_supervisor">
                        <a class="nav-link" onclick="document.getElementById('supervisorFormId').submit();"><fmt:message
                                key="user.supervisors"/></a>
                    </form>
                </li>
            </c:if>

            <!-- Users for supervisors-->
            <c:if test="${User.userType.name() == 'SUPERVISOR'}">
                <li class="nav-item">
                    <form id="usersOfSupervisorFormId" method="post" action="user_list_of_supervisor">
                        <input type="hidden" name="command" value="user_list_of_supervisor">
                        <a class="nav-link" onclick="document.getElementById('usersOfSupervisorFormId').submit();">
                            <fmt:message key="header.users"/>
                        </a>
                    </form>
                </li>
            </c:if>


            <!-- Users -->
            <c:if test="${User.userType.name() == 'ADMINISTRATOR'}">
                <li class="nav-item">
                    <form id="usersFormId" method="post" action="user_list">
                        <input type="hidden" name="command" value="user_list">
                        <a class="nav-link" onclick="document.getElementById('usersFormId').submit();">
                            <fmt:message key="header.users"/>
                        </a>
                    </form>
                </li>
            </c:if>

            <!-- Sign in -->
            <c:if test="${User == null}">
                <li class="nav-item">
                    <form id="signInFormId" method="post" action="sign_in">
                        <input type="hidden" name="command" value="visit_sign_in">
                        <a class="nav-link" onclick="document.getElementById('signInFormId').submit();">
                            <fmt:message key="header.sign_in"/>
                        </a>
                    </form>
                </li>
            </c:if>

            <!-- Profile -->
            <c:if test="${User != null}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="header.profile"/>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-primary pull-right"
                         aria-labelledby="navbarDropdownMenuLink">
                        <form id="dietFormId" method="post" action="diet">
                            <input type="hidden" name="command" value="diet">
                            <a class="dropdown-item" onclick="document.getElementById('dietFormId').submit();">
                                <fmt:message key="header.profile.diet"/>
                            </a>
                        </form>

                        <!--Settings-->
                        <form id="settingsFormId" method="post" action="settings">
                            <input type="hidden" name="command" value="visit_settings">
                            <a class="dropdown-item" onclick="document.getElementById('settingsFormId').submit();">
                                <fmt:message key="header.profile.settings"/>
                            </a>
                        </form>

                        <form id="signOutFormId" method="post" action="sign_out">
                            <input type="hidden" name="command" value="sign_out">
                            <a class="dropdown-item"
                               onclick="document.getElementById('signOutFormId').submit();"><fmt:message
                                    key="header.profile.sign_out"/></a>
                        </form>
                    </div>
                </li>
            </c:if>

            <!-- Language -->
            <li class="nav-item dropdown">
                <form method="post" action="translate">
                    <input type="hidden" name="command" value="translate">

                    <label>
                        <select onchange="this.form.submit()" class="custom-select" name="language">
                            <option value="Русский" <c:if test="${locale == 'ru'}"> selected </c:if>>Русский
                            </option>
                            <!-- value отправляется на сервер-->
                            <option value="English" <c:if test="${locale == 'en'}"> selected </c:if>>English
                            </option>
                            <option value="Français" <c:if test="${locale == 'fr'}"> selected </c:if>>Français
                            </option>
                        </select>
                    </label>
                </form>

            </li>

        </ul>
        <!-- Links -->


    </div>
    <!-- Collapsible content -->

</nav>
<!--/.Navbar-->

