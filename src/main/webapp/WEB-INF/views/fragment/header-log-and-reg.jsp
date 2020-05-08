<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="${contextPath}/signIn">Zaloguj</a></li>
            <li class="highlighted"><a href="${contextPath}/register">Załóż konto</a></li>
        </ul>
        <jsp:include page="/WEB-INF/views/fragment/header.jsp"/>
    </nav>
</header>

