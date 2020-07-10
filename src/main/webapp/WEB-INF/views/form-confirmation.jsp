<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl">
  <head>
    <jsp:include page="/WEB-INF/views/fragment/head.jsp"/>
    <title>Potwierdzenie wysłania formularza</title>
  </head>

  <body>

    <header class="header--form-page">

      <jsp:include page="/WEB-INF/views/fragment/nav-for-logged.jsp"/>

      <div class="slogan container container--90">
          <h2>
            Dziękujemy za przesłanie formularza. Na maila prześlemy wszelkie
            informacje o odbiorze.
          </h2>
      </div>
    </header>

    <jsp:include page="/WEB-INF/views/fragment/footer.jsp"/>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

  </body>
</html>
