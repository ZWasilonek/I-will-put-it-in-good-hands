<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<header>
    <c:choose>
        <c:when test="${userSession.firstName != null}">
            <nav class="container container--70">
                <ul class="nav--actions">
                    <li class="logged-user">
                        Witaj <c:out value="${userSession.firstName}"/>
                        <ul class="dropdown">
                            <li><a href="${contextPath}/user/edit">Profil</a></li>
                            <li><a href="${contextPath}/user/donations">Moje zbiórki</a></li>
                            <li><a href="<c:url value="/logout"/>">Wyloguj</a></li>
                        </ul>
                    </li>
                </ul>
                <jsp:include page="/WEB-INF/views/fragment/header.jsp"/>
            </nav>
        </c:when>
        <c:otherwise>
        <nav class="container container--70">
            <ul class="nav--actions">
                <li><a href="${contextPath}/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
                <li><a href="${contextPath}/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </ul>
            <jsp:include page="/WEB-INF/views/fragment/header.jsp"/>
        </nav>
        </c:otherwise>
    </c:choose>
</header>