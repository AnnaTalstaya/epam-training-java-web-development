<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="ctg" uri="customtags" %>

<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="products.product"/></title>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

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

</head>
<body>

<jsp:include page="header.jsp"/>

<div id="page-container">
    <div id="content-wrap">
        <c:if test="${error != null}">
            <div class="alert alert-danger block1" role="alert">
                    ${error}
            </div>
        </c:if>

        <div class="container-fluid h-100">
            <div class="row h-130">
                <aside class="col-12 col-md-2 p-0">
                    <nav class="navbar navbar-expand navbar-dark flex-md-column flex-row align-items-start">
                        <div class="collapse navbar-collapse">
                            <form method="get" action="product_list">

                                <ul class="flex-md-column flex-row navbar-nav w-100 justify-content-between">
                                    <li>
                                        <h3><fmt:message key="products.filters"/></h3>
                                        <br>
                                    </li>

                                    <!--Search-->
                                    <li>
                                        <label class="font-weight-bold" for="searchId"><fmt:message
                                                key="header.search_button"/></label>
                                        <input name="nameOrWordInName" value="${nameOrWordInName}"
                                               maxlength="100" pattern="^.{0,100}$"
                                               class="form-control mr-sm-2" id="searchId" type="text"
                                               placeholder="<fmt:message key="header.search_text"/>">
                                        <br>
                                    </li>

                                    <!--Cal-->
                                    <li>
                                        <label class="font-weight-bold" for="sortCal"><fmt:message
                                                key="product.calories"/></label>
                                        <div class="form-row" id="sortCal">
                                            <div class="form-group col-md-6">
                                                <label><fmt:message key="products.filter.min"/></label>
                                                <input name="minCalories" value="${minCalories}"
                                                       type="number" min="0" max="999999"
                                                       class="form-control" placeholder="${minCalories}">
                                            </div>
                                            <div class="form-group col-md-6 text-right">
                                                <label><fmt:message key="products.filter.max"/></label>
                                                <input name="maxCalories" value="${maxCalories}"
                                                       type="number" min="0" max="999999"
                                                       class="form-control" placeholder="${maxCalories}">
                                            </div>
                                        </div>
                                    </li>
                                    <!--Proteins-->
                                    <li>
                                        <label class="font-weight-bold" for="sortProt"><fmt:message
                                                key="product.proteins"/></label>
                                        <div class="form-row" id="sortProt">
                                            <div class="form-group col-md-6">
                                                <label><fmt:message key="products.filter.min"/></label>
                                                <input name="minProteins" value="${minProteins}"
                                                       type="number" min="0" max="999999"
                                                       class="form-control" placeholder="${minProteins}">
                                            </div>
                                            <div class="form-group col-md-6 text-right">
                                                <label><fmt:message key="products.filter.max"/></label>
                                                <input name="maxProteins" value="${maxProteins}"
                                                       type="number" min="0" max="999999"
                                                       class="form-control" placeholder="${maxProteins}">
                                            </div>
                                        </div>
                                    </li>
                                    <!--Lipids-->
                                    <li>
                                        <label class="font-weight-bold" for="sortLip"><fmt:message
                                                key="product.lipids"/></label>
                                        <div class="form-row" id="sortLip">
                                            <div class="form-group col-md-6">
                                                <label><fmt:message key="products.filter.min"/></label>
                                                <input name="minLipids" value="${minLipids}"
                                                       type="number" min="0" max="999999"
                                                       class="form-control"
                                                       placeholder="${minLipids}">
                                            </div>
                                            <div class="form-group col-md-6 text-right">
                                                <label><fmt:message key="products.filter.max"/></label>
                                                <input name="maxLipids" value="${maxLipids}"
                                                       type="number" min="0" max="999999"
                                                       class="form-control"
                                                       placeholder="${maxLipids}">
                                            </div>
                                        </div>
                                    </li>
                                    <!--Carbohydrates-->
                                    <li>
                                        <label class="font-weight-bold" for="sortCarb"><fmt:message
                                                key="product.carbohydrates"/></label>
                                        <div class="form-row" id="sortCarb">
                                            <div class="form-group col-md-6">
                                                <label><fmt:message key="products.filter.min"/></label>
                                                <input name="minCarbohydrates" value="${minCarbohydrates}"
                                                       type="number" min="0" max="999999"
                                                       class="form-control" placeholder="${minCarbohydrates}">
                                            </div>
                                            <div class="form-group col-md-6 text-right">
                                                <label><fmt:message key="products.filter.max"/></label>
                                                <input name="maxCarbohydrates" value="${maxCarbohydrates}"
                                                       type="number" min="0" max="999999"
                                                       class="form-control" placeholder="${maxCarbohydrates}">
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <button class="btn btn-success btn-circle text-uppercase" type="submit">
                                            <fmt:message key="products.filter.apply"/>
                                        </button>
                                    </li>
                                </ul>
                            </form>
                        </div>
                    </nav>
                </aside>

                <main class="col">
                    <div class="container-fluid">
                        <div class="container">
                            <div class="row text-center">
                                <c:choose>
                                    <c:when test="${searchError != null}">
                                        ${searchError}
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${products.size() > 0}">
                                            <c:forEach begin="${startIndexOfProductList}"
                                                       end="${startIndexOfProductList + productsPerPage - 1}"
                                                       var="product"
                                                       items="${products}">

                                                <div class="col-xs-6 col-sm-4 col-lg-3 ex2">
                                                    <form method="get" action="show_product_details">
                                                        <input type="hidden" name="productId" value="${product.productId}">

                                                        <img src="${pageContext.request.contextPath}/images/products/${product.imageURL}"
                                                             alt=""
                                                             width="200" height="250">
                                                        <p>${product.name}</p>
                                                        <button type="submit" class="btn btn-primary center-block">
                                                            <fmt:message key="product.details"/>
                                                        </button>
                                                    </form>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>


                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <c:if test="${searchError == null}">
            <ctg:pagination startIndexOfObjectList="${startIndexOfProductList}"
                            objectsPerPage="${productsPerPage}"
                            indexOfPage="${indexOfPage}"
                            numberOfObjects="${products.size()}"
                            locale="${locale}"
                            commandValue="product_list"
                            nameOrWordInName="${nameOrWordInName}"
                            minCalories="${minCalories}"
                            minProteins="${minProteins}"
                            minLipids="${minLipids}"
                            minCarbohydrates="${minCarbohydrates}"
                            maxCalories="${maxCalories}"
                            maxProteins="${maxProteins}"
                            maxLipids="${maxLipids}"
                            maxCarbohydrates="${maxCarbohydrates}"
            />
        </c:if>

    </div>
    <jsp:include page="footer.jsp"/>

</div>

</body>
</html>
