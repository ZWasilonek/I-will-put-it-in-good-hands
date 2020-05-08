<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="pl">
  <head>
<%--    <meta name="_csrf" content="${_csrf.token}"/>--%>
<%--    <meta name="_csrf_header" content="${_csrf.headerName}"/>--%>
    <jsp:include page="/WEB-INF/views/fragment/head.jsp"/>
    <title>Sign In</title>
  </head>
  <body>

    <jsp:include page="/WEB-INF/views/fragment/header-log-and-reg.jsp"/>

    <section class="login-page">
      <h2>Zaloguj się</h2>
      <form:form modelAttribute="loginUser" method="post">

        <spring:bind path="email">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <form:input type="email" path="email" placeholder="Email" />
          <form:errors path="email" cssClass="error" element="div"/>
        </div>
        </spring:bind>

        <spring:bind path="password">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <form:input type="password" path="password" placeholder="Hasło" />
          <form:errors path="password" cssClass="error" element="div"/>
          <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>
        </spring:bind>

<%--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

        <div class="form-group form-group--buttons">
          <a href="${contextPath}/register" class="btn btn--without-border">Załóż konto</a>
          <button class="btn" type="submit">Zaloguj się</button> 
        </div>
      </form:form>
    </section>

    <jsp:include page="/WEB-INF/views/fragment/footer.jsp"/>

  </body>
</html>
