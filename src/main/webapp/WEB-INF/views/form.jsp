<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="pl">

  <head>
    <%@include file="/WEB-INF/views/fragment/head.jspf"%>
    <title>Donation form</title>
  </head>

  <body>
    <header class="header--form-page">

      <jsp:include page="/WEB-INF/views/fragment/nav-for-logged.jsp"/>

      <div class="slogan container container--90">
        <div class="slogan--item">
          <h1>
            Oddaj rzeczy, których już nie chcesz<br />
            <span class="uppercase">potrzebującym</span>
          </h1>

          <div class="slogan--steps">
            <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
            <ul class="slogan--steps-boxes">
              <li>
                <div><em>1</em><span>Wybierz rzeczy</span></div>
              </li>
              <li>
                <div><em>2</em><span>Spakuj je w worki</span></div>
              </li>
              <li>
                <div><em>3</em><span>Wybierz fundację</span></div>
              </li>
              <li>
                <div><em>4</em><span>Zamów kuriera</span></div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </header>

    <section class="form--steps">
      <div class="form--steps-instructions">
        <div class="form--steps-container">
          <h3>Ważne!</h3>
          <p data-step="1" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="2">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="3">
           Wybierz jedną, do
            której trafi Twoja przesyłka.
          </p>
          <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
      </div>

      <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <!-- STEP 1: class .active is switching steps -->
        <form:form modelAttribute="donationForm" method="post" id="donationForm">
          <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>

            <c:forEach var="category" items="${categories}">
            <div class="form-group form-group--checkbox">
              <label>
                <form:checkbox path="categories" itemValue="${category.id}" value="${category.id}" cssClass="categoryIdInput"/>
                <span class="checkbox"></span>
                <span class="description"><c:out value="${category.name}"/></span>
              </label>
            </div>
            </c:forEach>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 2 -->
          <div data-step="2">
            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

            <div class="form-group form-group--inline">
              <label>
                Liczba 60l worków:
                <form:input path="bagsQuantity" type="number" step="1" min="1" max="100" id="quantityInput" required="required"/>
              </label>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 3 -->
          <div data-step="3">
            <h3>Wybierz organizację, której chcesz pomóc:</h3>

            <c:forEach var="institution" items="${institutions}">
            <div class="form-group form-group--checkbox">
              <label>
                <form:radiobutton path="institution.id" value="${institution.id}" cssClass="foundationNameInput"/>
                <span class="checkbox radio"></span>
                <span class="description">
                  <div class="title">${institution.name}</div>
                  <div class="subtitle">
                    Cel i misja: ${institution.description}
                  </div>
                </span>
              </label>
            </div>
            </c:forEach>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 4 -->
          <div data-step="4">
            <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

            <div class="form-section form-section--columns">
              <div class="form-section--column">
                <h4>Adres odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Ulica <form:input path="shippingAddress.street" type="text" id="streetInput" required="required"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Miasto <form:input path="shippingAddress.city" type="text" id="cityInput" required="required"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Kod pocztowy <form:input path="shippingAddress.zipCode" type="text" id="zipCodeInput" required="required"/>
                  </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Numer telefonu <form:input path="shippingAddress.phoneNumber" type="tel" id="phoneInput" required="required"/>
                  </label>
                </div>
              </div>

              <div class="form-section--column">
                <h4>Termin odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Data <form:input path="shippingAddress.pickUpDate" type="date" formatter="yyyy-MM-dd" id="dateInput" required="required"/> </label>
                </div>
                
                <div class="form-group form-group--inline">
                  <label> Godzina <form:input path="shippingAddress.pickUpTime" type="time" formatter="HH:mm" id="hourInput" required="required"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Uwagi dla kuriera
                    <form:textarea path="shippingAddress.pickUpComment" rows="5" id="commentsTextarea"/>
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 5 -->
          <div data-step="5">
            <h3>Podsumowanie Twojej darowizny</h3>

            <div class="summary">
              <div class="form-section">
                <h4>Oddajesz:</h4>
                <ul>
                  <li>
                    <span class="icon icon-bag"></span>
                    <span class="summary--text">
                      <span id="quantity"></span> <span id="bagsNumber"></span> <span id="categoryName"></span>
                    </span>
                  </li>

                  <li>
                    <span class="icon icon-hand"></span>
                    <span class="summary--text">
                      Dla <span class="foundationName"></span> w Warszawie<span class="foundationCity"></span>
                    </span>
                  </li>
                </ul>
              </div>

              <div class="form-section form-section--columns">
                <div class="form-section--column">
                  <h4>Adres odbioru:</h4>
                  <ul>
                    <li><span class="shipping-info">Ulica: &nbsp;</span><span id="streetLi"></span></li>
                    <li><span class="shipping-info">Miasto: &nbsp;</span><span id="cityLi"></span></li>
                    <li><span class="shipping-info">Kod pocztowy: &nbsp;</span><span id="zipCodeLi"></span></li>
                    <li><span class="shipping-info">Telefon: &nbsp;</span><span id="phoneLi"></span></li>
                  </ul>
                </div>

                <div class="form-section--column">
                  <h4>Termin odbioru:</h4>
                  <ul>
                    <li><span class="shipping-info">Data: &nbsp;</span><span id="dateLi"></span></li>
                    <li><span class="shipping-info">Godzina: &nbsp;</span><span id="hourLi"></span></li>
                    <li><span class="shipping-info">Uwagi: &nbsp;</span><span id="commentsLi">Brak uwag</span></li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="submit" class="btn">Potwierdzam</button>
            </div>
          </div>
        </form:form>
      </div>
    </section>

    <%@include file="/WEB-INF/views/fragment/footer.jspf"%>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/form.js"/>"></script>

  </body>

</html>