<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <jsp:include page="/WEB-INF/views/fragment/head.jsp"/>
        <title>Panel użytkownika</title>
    </head>
<body>
    <div>
        <jsp:include page="/WEB-INF/views/fragment/header-log-and-reg.jsp"/>

        <section class="login-page">
            <h2>Twoje dane</h2>

            <form:form modelAttribute="userFromSession" method="post">

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

                <c:if test="${userSaved}">
                    <div class="user-updated">Twoje dane zostały zaktualizowane</div>
                </c:if>

                <div class="form-group form-group--buttons">
                    <button class="btn btn--without-border">Zapisz</button>
                    <a href="#" class="btn" id="removeUserBtn" type="submit">Usuń konto</a>
                </div>
            </form:form>
        </section>

        <jsp:include page="/WEB-INF/views/fragment/footer.jsp"/>
        <script src="<c:url value="/resources/js/user-edit-form.js"/>"></script>
    </div>
</body>
</html>
