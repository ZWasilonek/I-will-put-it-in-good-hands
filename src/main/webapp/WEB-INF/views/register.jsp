<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <%@include file="/WEB-INF/views/fragment/head.jspf"%>
    <title>Sign Up</title>
  </head>
  <body>

    <jsp:include page="/WEB-INF/views/fragment/header-log-and-reg.jsp"/>

    <section class="login-page">
      <h2>Załóż konto</h2>

      <form:form modelAttribute="registeredUser" method="post">

        <spring:bind path="firstName">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="firstName" placeholder="First name" />
            <form:errors path="firstName" cssClass="error" element="div"/>
          </div>
        </spring:bind>

        <spring:bind path="lastName">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="lastName" placeholder="Last name" />
            <form:errors path="lastName" cssClass="error" element="div"/>
          </div>
        </spring:bind>

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
        </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
        <div class="form-group">
          <form:input type="password" path="confirmPassword" placeholder="Powtórz hasło" />
          <form:errors path="confirmPassword" cssClass="error" element="div"/>
        </div>
        </spring:bind>

        <div class="form-group form-group--buttons">
          <a href="${contextPath}/login" class="btn btn--without-border">Zaloguj się</a>
          <button class="btn" type="submit">Załóż konto</button>
        </div>
      </form:form>
    </section>

    <%@include file="/WEB-INF/views/fragment/footer.jspf"%>

  </body>
</html>
