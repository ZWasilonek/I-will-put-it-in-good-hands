<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="pl">

    <head>
        <jsp:include page="/WEB-INF/views/fragment/head.jsp"/>
        <title>Charity</title>
    </head>

    <body>

        <header class="header--main-page">
            <jsp:include page="/WEB-INF/views/fragment/header-log-and-reg.jsp"/>
            <div class="slogan container container--90">
                <div class="slogan--item">
                    <h1>
                        Zacznij pomagać!<br/>
                        Oddaj niechciane rzeczy w zaufane ręce
                    </h1>
                </div>
            </div>
        </header>

        <section class="stats">
            <div class="container container--85">
                <div class="stats--item">
                    <em><c:out value="${allBags}"/></em>

                    <h3>Oddanych worków</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
                        tempora!
                    </p>
                </div>

                <div class="stats--item">
                    <em><c:out value="${allDonations}"/></em>
                    <h3>Przekazanych darów</h3>
                    <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
                        quam.</p>
                </div>

            </div>
        </section>

        <a name="steps"/>
        <section class="steps">
            <h2>Wystarczą 4 proste kroki</h2>

            <div class="steps--container">
                <div class="steps--item">
                    <span class="icon icon--hands"></span>
                    <h3>Wybierz rzeczy</h3>
                    <p>ubrania, zabawki, sprzęt i inne</p>
                </div>
                <div class="steps--item">
                    <span class="icon icon--arrow"></span>
                    <h3>Spakuj je</h3>
                    <p>skorzystaj z worków na śmieci</p>
                </div>
                <div class="steps--item">
                    <span class="icon icon--glasses"></span>
                    <h3>Zdecyduj komu chcesz pomóc</h3>
                    <p>wybierz zaufane miejsce</p>
                </div>
                <div class="steps--item">
                    <span class="icon icon--courier"></span>
                    <h3>Zamów kuriera</h3>
                    <p>kurier przyjedzie w dogodnym terminie</p>
                </div>
            </div>

            <a href="${contextPath}/register" class="btn btn--large">Załóż konto</a>
        </section>

        <a name="about"/>
        <section class="about-us">
            <div class="about-us--text">
                <h2>O nas</h2>
                <p>Projekt &#8222;Charity&#8221; przygotowany przez bootcamp CodersLab w ramach projektu PortfolioLab.<br>
                    Projekt zawierał gotowe szablony stron html: index(strona startowa - endpoint &#8222;/&#8221;), login(strona logowania - endpoint &#8222;/login&#8221;),
                    register(strona rejestracji - endpoint &#8222;/register&#8221;), form(formularz wysyłki darów - endpoint &#8222;/donation&#8221;) oraz
                    form-confirmation(strona potwierdzająca poprawną wysyłkę formularza - endpoint &#8222;/donation/confirm&#8221;).<br>
                    Praca polegała na dodaniu responsywności całej aplikacji w językach Java i JavaScript oraz poprawieniu istniejących interfejsów i uzupełnieniu projektu
                    o dodatkowe strony, tak aby były zgodne z przygotowanym przez UI designera standardzie.</p>
                <img src="<c:url value="/resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
            </div>
            <div class="about-us--image"><img src="<c:url value="/resources/images/about-us.jpg"/>" alt="People in circle"/>
            </div>
        </section>

        <a name="help"/>
        <section class="help">
            <h2>Komu pomagamy?</h2>

            <!-- SLIDE 1 -->
            <div class="help--slides active" data-id="1">
                <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.
                    Możesz sprawdzić czym się zajmują.</p>

                <ul class="help--slides-items institutions-list">
                    <c:forEach var="institution" items="${institutions}">
                        <li class="institution-list-item">
                            <div class="col">
                                <div class="title"><c:out value="${institution.name}"/></div>
                                <div class="subtitle">Cel i misja: <c:out value="${institution.description}"/></div>
                            </div>
                        </li>
    <%--                    <c:forEach var="institution" items="${institutions}" varStatus="counter" begin="0" end="2">--%>
    <%--                    <li class="institution-list-item">--%>
    <%--                        <div class="col">--%>
    <%--                            <div class="title"><c:out value="${institutions.get(counter).name}"/></div>--%>
    <%--                            <div class="subtitle">Cel i misja: <c:out value="${institution.description}"/></div>--%>
    <%--                        </div>--%>
    <%--                    </li>--%>
    <%--                    <li class="institution-list-item">--%>
    <%--                        <div class="col">--%>
    <%--                            <div class="title"><c:out value="${institutions.get(counter + 1).name}"/></div>--%>
    <%--                            <div class="subtitle">Cel i misja: <c:out value="${institution.description}"/></div>--%>
    <%--                        </div>--%>
    <%--                    </li>--%>
                    </c:forEach>
                </ul>
            </div>
        </section>

        <jsp:include page="/WEB-INF/views/fragment/footer.jsp"/>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    </body>

</html>