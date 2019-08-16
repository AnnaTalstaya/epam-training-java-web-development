<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--jstl tag--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="locale"
       value="${not empty locale ? locale : 'en'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>${product.name}</title>

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

<jsp:include page="header.jsp"/>

<div id="page-container">
    <div id="content-wrap">
        <div class="container">
            <div class="card">
                <div class="container-fliud">
                    <div class="wrapper row">
                        <div class="preview col-md-4">

                            <div class="preview-pic tab-content">
                                <div class="tab-pane active" id="pic-1"><img
                                        src="${pageContext.request.contextPath}/images/products/${product.imageURL}"
                                        width="200"
                                        height="250"/></div>
                            </div>

                        </div>
                        <div class="details col-md-6" style="padding-left: 80px;">
                            <h3 class="product-title">${product.name}</h3>

                            <p class="product-description">${product.description}</p>
                            <h5 class="sizes"><fmt:message key="product.calories"/>
                                <span class="size" data-toggle="tooltip" title="small">${product.calories}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="product.proteins"/>
                                <span class="size" data-toggle="tooltip" title="small">${product.proteins}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="product.lipids"/>
                                <span class="size" data-toggle="tooltip" title="small">${product.lipids}</span>
                            </h5>
                            <h5 class="sizes"><fmt:message key="product.carbohydrates"/>
                                <span class="size" data-toggle="tooltip" title="small">${product.carbohydrates}</span>
                            </h5>


                            <c:choose>
                                <c:when test="${User == null}">
                                    <p style="color: red"><fmt:message key="product.disabled.add"/></p>
                                </c:when>

                                <c:otherwise>
                                    <form method="post" action="add_meal" class="form-horizontal">
                                        <input type="hidden" name="command" value="add_meal">
                                        <input type="hidden" name="productId" value="${product.productId}">

                                        <label>
                                            <input type="date" name="mealDate" value="${mealDate}" maxlength="20"
                                                   id="dateOfBirth"
                                                   class="form-control" required>
                                        </label>

                                        <label>
                                            <select class="custom-select" name="mealTime" required>
                                                <option ${mealTime=="breakfast"?"selected":""} value="breakfast">
                                                    <fmt:message
                                                            key="product.breakfast"/>
                                                </option>
                                                <!-- value отправляется на сервер-->
                                                <option ${mealTime=="lunch"?"selected":""} value="lunch"><fmt:message
                                                        key="product.lunch"/>
                                                </option>
                                                <option ${mealTime=="dinner"?"selected":""} value="dinner"><fmt:message
                                                        key="product.dinner"/>
                                                </option>
                                            </select>
                                        </label>

                                        <!--Quantity -->
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"
                                                      id="inputGroup-sizing-default" style="width: 100px;">
                                                    <fmt:message key="product.quantity"/>
                                                </span>
                                            </div>
                                            <input type="number" min="1" max="999"
                                                   name="quantity" value="${quantity}"

                                                   class="form-control" aria-label="Sizing example input"
                                                   aria-describedby="inputGroup-sizing-default">
                                            <span style="color:red;">${errorQuantity}</span>
                                        </div>


                                        <div class="action">
                                            <button class="add-to-cart btn btn-default" type="submit"><fmt:message
                                                    key="product.add"/></button>
                                        </div>

                                    </form>

                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <jsp:include page="footer.jsp"/>

</div>


</body>
</html>




