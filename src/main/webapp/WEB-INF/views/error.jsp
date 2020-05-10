<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="pl">
<body>

<div class="container">

    <div class="starter-template">
        <h1>403 - Odmowa dostępu</h1>
        <div>Witaj <c:out value="${userSession.firstName}"/>,
            nie masz zgody na dostęp do tej strony.</div>
    </div>

</div>
</body>
</html>
