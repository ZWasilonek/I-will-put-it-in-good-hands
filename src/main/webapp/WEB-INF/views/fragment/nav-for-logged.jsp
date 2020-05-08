<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav class="container container--70">
    <ul class="nav--actions">
        <li class="logged-user">
            Witaj <c:out value="${userSession.firstName}"/>
            <ul class="dropdown">
                <li><a href="${contextPath}/user">Profil</a></li>
                <li><a href="#">Moje zbiórki</a></li>
                <li><input formaction="${contextPath}/logout" formmethod="post" type="submit" value="Wyloguj"></li>
            </ul>
        </li>
    </ul>
    <c:set var="userPanel" value="true" scope="session"/>
    <jsp:include page="/WEB-INF/views/fragment/header.jsp"/>
</nav>