<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:choose>
    <c:when test="${userPanel eq true}">
        <ul>
            <li><a href="${contextPath}/" class="btn btn--without-border active">Start</a></li>
            <li><a href="${contextPath}/#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="${contextPath}/#about" class="btn btn--without-border">O nas</a></li>
            <li><a href="${contextPath}/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="${contextPath}/donation" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </c:when>
    <c:otherwise>
        <ul>
            <li><a href="${contextPath}/" class="btn btn--without-border active">Start</a></li>
            <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#about" class="btn btn--without-border">O nas</a></li>
            <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="${contextPath}/donation" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </c:otherwise>
</c:choose>
