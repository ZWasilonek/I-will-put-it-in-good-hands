<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="pl">

<head>
    <jsp:include page="/WEB-INF/views/fragment/head.jsp"/>
    <title>Panel użytkownika</title>
</head>

<body>
<header class="header--form-page">

    <jsp:include page="/WEB-INF/views/fragment/nav-for-logged.jsp"/>

    <div class="carousel-wrapper" id="userDonationsContentDiv">
        <div class="user-item-content carousel">
            <c:forEach var="donation" items="${userDonations}" varStatus="page">
                <div class="carousel-item ${page.index == 0 ? 'initial' : ''}">
                    <div class="user-panel--item-counter">
                        <span><c:out value="${page.index + 1}"/>
                        </span>/<c:out value="${userDonations.size()}"/>
                    </div>
                    <h3>Podsumowanie Twojej <span><c:out value="${page.index+1}"/></span> darowizny</h3>
                    <div>
                        <div class="summary">
                            <div class="form-section">
                                <ul>
                                    <li>
                                        <span class="icon icon-bag"></span>
                                        <span class="summary--text">
                                            <c:out value="${donation.quantity}"/>
                                            <span id="bagsNumber"></span>

                                            <c:forEach var="category" items="${donation.categories}">
                                                <input type="hidden" value="${category.id}" class="categoryIdInput"/>
                                                <span id="categoryName"></span>
                                            </c:forEach>
                                        </span>
                                    </li>

                                    <li>
                                        <span class="icon icon-hand"></span>
                                        <span class="summary--text">
                                            <input type="hidden" value="${donation.institution.id}" class="foundationNameInput">
                                        Dla <span class="foundationName"></span> w Warszawie<span class="foundationCity"></span>
                                        </span>
                                    </li>
                                </ul>
                            </div>

                            <div class="reception-section--columns">
                                <div class="form-section--column">
                                    <h4>Adres odbioru:</h4>
                                    <ul>
                                        <li><c:out value="${donation.street}"/></li>
                                        <li><c:out value="${donation.city}"/></li>
                                        <li><c:out value="${donation.zipCode}"/></li>
                                    </ul>
                                </div>

                                <div class="form-section--column">
                                    <h4>Termin odbioru:</h4>
                                    <ul>
                                        <li><c:out value="${donation.pickUpDate}"/></li>
                                        <li><c:out value="${donation.pickUpTime}"/></li>
                                        <li><c:out value="${donation.pickUpComment}"/></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel__button--next"></div>
                    <div class="carousel__button--prev"></div>
                </div>
            </c:forEach>
        </div>
    </div>



    <div class="user-panel--item-container" id="userInstitutionsContentDiv">
    <!-- STEP 2 -->
        <div>
            <c:forEach var="institution" items="${userInstitutions}">
                <div class="form-group">
                    <div class="title">${institution.name}</div>
                    <div class="subtitle">
                        Cel i misja: ${institution.description}
                    </div>
                </div>
            </c:forEach>

            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>
    </div>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1 class="hideUserNameH1">Witaj <c:out value="${sessionUser.firstName}"/></h1>

            <div class="slogan--steps">
                <ul class="slogan--steps-boxes">
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

<%--<jsp:include page="/WEB-INF/views/fragment/footer.jsp"/>--%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
