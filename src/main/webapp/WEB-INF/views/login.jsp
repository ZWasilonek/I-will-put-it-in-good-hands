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
    <title>Sign In</title>
  </head>
  <body>

    <jsp:include page="/WEB-INF/views/fragment/header-log-and-reg.jsp"/>

    <section class="login-page">
      <h2>Zaloguj się</h2>
      <form:form modelAttribute="loginUser" method="post">

        <div class="form-group">
          <form:input type="email" path="email" placeholder="Email" />
        </div>

        <div class="form-group">
          <form:input type="password" path="password" placeholder="Hasło" />
          <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <c:if test="${param.error != null}">
          <div class="error">
            Nieprawidłowy login lub hasło.<br>
<%--            Reason: ${SPRING_SECURITY_LAST_EXCEPTION.message}--%>
          </div>
        </c:if>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        
        <div class="form-group form-group--buttons">
          <a href="${contextPath}/register" class="btn btn--without-border">Załóż konto</a>
          <button class="btn" type="submit">Zaloguj się</button> 
        </div>
      </form:form>
    </section>

    <jsp:include page="/WEB-INF/views/fragment/footer.jsp"/>

  </body>
</html>
