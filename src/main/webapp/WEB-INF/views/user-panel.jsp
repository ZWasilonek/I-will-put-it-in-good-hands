<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="pl">

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <jsp:include page="/WEB-INF/views/fragment/head.jsp"/>
    <title>Panel użytkownika</title>
</head>

<body>
<header class="header--form-page">

    <jsp:include page="/WEB-INF/views/fragment/nav-for-logged.jsp"/>

    <div class="carousel slide" id="userDonationsContentDiv" data-ride="carousel">
        <div class="user-item-content carousel-inner">
            <c:forEach var="donation" items="${userDonations}" varStatus="page">
                <div class="user-panel--item carousel-item ${page.index == 0 ? 'active' : ''}">
                    <div>
                        <span><c:out value="${page.index + 1}"/> /
                        </span><c:out value="${userDonations.size()}"/>
                    </div>
                    <h3>Podsumowanie Twojej <span><c:out value="${page.index+1}"/></span> darowizny</h3>
                    <div>
                        <div class="summary donation-summary">
                            <div class="form-section">
                                <ul>
                                    <li>
                                        <span class="icon icon-bag"></span>
                                        <span class="summary--text">
                                            <span id="quantityInput"><c:out value="${donation.bagsQuantity}"/></span>
                                            <span id="bagsNumber"> x </span>

                                            <c:forEach var="category" items="${donation.categories}" varStatus="status">
                                                <span class="categoryIdInput" id="categoryName">
                                                    <c:out value="${category.name}"/><c:if test="${not status.last}">, </c:if>
                                                </span>
                                            </c:forEach>
                                        </span>
                                    </li>

                                    <li>
                                        <span class="icon icon-hand"></span>
                                        <span class="summary--text">
                                            <input type="hidden" value="" class="foundationNameInput">
                                            <span class="foundationName foundationNameInput">
                                                <c:out value="${donation.institution.name}"/>
                                            </span>
                                            w Warszawie<span class="foundationCity"></span>
                                        </span>
                                    </li>
                                </ul>
                            </div>

                            <div class="reception-section--columns">
                                <div class="form-section--column">
                                    <h4>Adres odbioru:</h4>
                                    <ul>
                                        <li><c:out value="${donation.shippingAddress.street}"/></li>
                                        <li><c:out value="${donation.shippingAddress.city}"/></li>
                                        <li><c:out value="${donation.shippingAddress.zipCode}"/></li>
                                    </ul>
                                </div>

                                <div class="form-section--column">
                                    <h4>Termin odbioru:</h4>
                                    <ul>
                                        <li><c:out value="${donation.shippingAddress.pickUpDate}"/></li>
                                        <li><c:out value="${donation.shippingAddress.pickUpTime}"/></li>
                                        <li><c:out value="${donation.shippingAddress.pickUpComment}"/></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <a class="carousel-control-prev" href="#userDonationsContentDiv" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon bg-warning" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#userDonationsContentDiv" role="button" data-slide="next">
            <span class="carousel-control-next-icon bg-warning ml-5" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


    <div class="carousel slide" id="userInstitutionsContentDiv" data-ride="carousel">
        <div class="user-item-content carousel-inner">
            <c:forEach var="institution" items="${userInstitutions}">
                <div class="user-panel--item carousel-item">
                    <div class="title">${institution.name}</div>
                    <div class="subtitle">
                        Cel i misja: ${institution.description}
                    </div>
                </div>
            </c:forEach>
            <a class="carousel-control-prev" href="#userInstitutionsContentDiv" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon bg-warning" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#userInstitutionsContentDiv" role="button" data-slide="next">
                <span class="carousel-control-next-icon bg-warning ml-5" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>

    <div class="container-user-panel container--90">
        <div class="slogan--item">
            <h1 class="hideUserNameH1">Witaj <c:out value="${userSession.firstName}"/></h1>

            <div class="slogan--steps">
                <ul class="slogan--steps-boxes slogan-content">
                    <li>
                        <div><em><br></em>
                            <span>
                                <a href="#userDonationsContentDiv" id='userDonationsContentDiv-link' class="showUserContent" rel="userDonationsContentDiv">
                                Twoje zbiórki
                                </a>
                            </span>
                        </div>
                    </li>
                    <li>
                        <div><em><c:out value="${userInstitutions.size()}"/></em>
                            <span>
                                <a href="#userInstitutionsContentDiv" id="userInstitutionsContentDiv-link" class="showUserContent" rel='userInstitutionsContentDiv'>
                                Wybrane fundacje
                                </a>
                            </span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <em><c:out value="${allUserBags}"/></em>
                            <span>
                                przekazanych worków
                            </span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <em><c:out value="${userDonations.size()}"/></em>
                            <span>
                                przekazanych darów
                            </span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
