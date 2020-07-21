document.addEventListener("DOMContentLoaded", function() {
  const categoryIdInputArray = [];

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }

  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function (e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      const setActive = () => {
        this.slides.forEach(slide => {
          slide.classList.remove("active");

          if (slide.dataset.step == this.currentStep) {
            slide.classList.add("active");
          }
        });
        this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
        this.$step.parentElement.hidden = this.currentStep >= 5;
      }

      //VALIDATION
      const checkCategory = () => {
        let isCategoryCorrect = validateCategory();
        if (isCategoryCorrect) {
          setActive();
        } else {
          this.currentStep--;
          this.$step.innerText = this.currentStep;
        }
      }

      const checkBagsQuantity = () => {
        let isBagsNumbEntered = validateBagsQuantity();
        if (isBagsNumbEntered) {
          setActive();
        } else {
          this.currentStep--;
          this.$step.innerText = this.currentStep;
        }
      }

      const checkFoundation = () => {
        let isFoundationSelected = validateFoundation();
        if (isFoundationSelected) {
          setActive();
        } else {
          this.currentStep--;
          this.$step.innerText = this.currentStep;
        }
      }

      const checkDeliveryDetails = () => {
        let isDeliveryDetailsCorrect = validateDeliveryDetails();
        if (isDeliveryDetailsCorrect) {
          setActive();
        } else {
          this.currentStep--;
          this.$step.innerText = this.currentStep;
        }
      }

      switch (this.currentStep) {
        case 1:
          setActive();
          break;
        case 2:
          checkCategory();
          break;
        case 3:
          checkBagsQuantity();
          break;
        case 4:
          checkFoundation();
          break;
        case 5:
          checkDeliveryDetails();
          break;
      }
    }

  }

  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }

  /**
   * Update form front-end
   * Show quantity of bags and category name.
   */
  const bagNumber = ["worek", "worki", "worków"];
  const categoriesName = new Map();
  categoriesName.set('1', 'ubrań w dobrym stanie dla dzieci');
  categoriesName.set('2', 'ubrań do wyrzucenia');
  categoriesName.set('3', 'zabawek');
  categoriesName.set('4', 'książek');
  categoriesName.set('5', 'innych');

  setCategoryIdInputArray();
  displayBagsQuantity()

  /** STEP 1 - SELECT THE CATEGORY **/
  function setCategoryIdInputArray() {
    $('.categoryIdInput').on('change', function () {
      $('input[class = categoryIdInput]:checked').each(function () {
        const categoryID = $(this).val();
        if (categoryIdInputArray.indexOf(categoryID) === -1) {
          categoryIdInputArray.push(categoryID);
        }
      });

      $('input[class = categoryIdInput]:not(:checked)').each(function () {
        const categoryID = $(this).val();

        if (categoryIdInputArray.length !== 0 && categoryID !== "") {
          categoryIdInputArray.forEach((categoryVal) => {
            if (categoryVal === categoryID) {
              categoryIdInputArray.pop();
            }
          })
        }
      })
      validateCategory();
    });

    /**
     * Update form front-end
     * Hide input from 1-form donation.
     */
    $("#donationForm").append("<input type='hidden' value=on>");
    $("input[type='hidden']").remove();
  }

  function getSelectedCategories(categoryIdArr) {
    categoryIdArr.forEach((id, index) => {
      const $idCategoryName = $("#categoryName");
      if (categoriesName.get(id)) {
        const spamItem = categoriesName.get(id);
        if (index === categoryIdArr.length - 2) {
          $idCategoryName.append(spamItem + " i ");
        } else if (index === categoryIdArr.length - 1) {
          $idCategoryName.append(spamItem);
        } else $idCategoryName.append(spamItem + ", ");
      }
    })
  }

  /** STEP 2 - ENTER QUANTITY OF BAGS **/
  function displayBagsQuantity() {
    const $bagsNumbInput = $("#quantityInput");
    // $bagsNumbInput.val(1);
    $bagsNumbInput.on('change', function () {
      const $classBagsNum = $("#bagsNumber");
      let quantityVal = $(this).val();
      $("#quantity").text(quantityVal);

      switch (quantityVal) {
        case "1":
          $classBagsNum.text(bagNumber[0]);
          getSelectedCategories(categoryIdInputArray);
          break;
        case "2":
        case "3":
        case "4":
          $classBagsNum.text(bagNumber[1]);
          getSelectedCategories(categoryIdInputArray);
          break;
        default:
          $classBagsNum.text(bagNumber[2]);
          getSelectedCategories(categoryIdInputArray);
          break;
      }
    });
  }

  /** STEP 3 - SELECT FOUNDATION **/
  /**
   * Update form front-end
   * Show institution name and city.
   */
  const institutionsNamesMap = new Map();
  institutionsNamesMap.set('1','Fundacji “Dbam o Zdrowie”');
  institutionsNamesMap.set('2','Fundacji “A kogo”');
  institutionsNamesMap.set('3','Fundacji “Dla dzieci”');
  institutionsNamesMap.set('4','Fundacji “Bez domu”');

  let selectedFoundation = null;

  $('.foundationNameInput').on('change', function() {
    const selectedInstitutionRadioId = $('input[class=foundationNameInput]:checked').val();
    for (let [key] of institutionsNamesMap) {
      if (selectedInstitutionRadioId === key) {
        selectedFoundation = key;
        $(".foundationName").text(institutionsNamesMap.get(key));
        break;
      }
    }
  });

  $(".foundationCityInput").on("change paste keyup", function() {
    let foundationCityValue = $(this).val();
    $(".foundationCity").text(foundationCityValue);
  });

  /** STEP 4 - GET ADDRESS **/
  /**
   * Update form front-end
   * Show pickup address.
   */
  const $streetInput = $("#streetInput");
  const $cityInput = $("#cityInput");
  const $zipCodeInput = $("#zipCodeInput");
  const $phoneInput = $("#phoneInput");

  $streetInput.on("change paste keyup", function() {
    let streetValue = $(this).val();
    $("#streetLi").text(streetValue);
  });

  $cityInput.on("change paste keyup", function() {
    let cityVal = $(this).val();
    $("#cityLi").text(cityVal);
  });

  $zipCodeInput.on("change paste keyup", function() {
    let zipCodeVal = $(this).val();
    $("#zipCodeLi").text(zipCodeVal);
  });

  $phoneInput.on("change paste keyup", function() {
    let phoneVal = $(this).val();
    $("#phoneLi").text(phoneVal);
  });

  /**
   * Update form front-end
   * Show pickup date.
   */
  const $dateInput = $("#dateInput");
  const $hourInput = $("#hourInput");
  const $commentsTextarea = $("#commentsTextarea");

  $dateInput.on("change paste keyup", function() {
    let dateVal = $(this).val();
    $("#dateLi").text(dateVal);
  });

  $hourInput.on("change paste keyup", function() {
    let hourVal = $(this).val();
    $("#hourLi").text(hourVal);
  });

  $commentsTextarea.on("change paste keyup", function() {
    let commentsVal = $(this).val();
    if (commentsVal !== '') {
      $("#commentsLi").text(commentsVal);
    }
  });

  /** VALIDATIONS **/
  //Step 1 - choose category
  function validateCategory() {
    const ERROR = "Musisz wybrać jakąś kategorię";
    const categoryError = $(".category-error");

    (function() {
      if (!isCategorySelected()) {
        showErrorMessage();
      } else {
        categoryError.hide();
      }
    })();

    function isCategorySelected() {
      return categoryIdInputArray.length !== 0;
    }

    function showErrorMessage() {
      if (categoryError.length === 0) {
        let errorP = document.createElement("p");
        errorP.innerHTML = ERROR;
        errorP.classList.add("form-donation-error");
        errorP.classList.add("category-error")
        $('.form--steps-counter').append(errorP);
      } else {
        if (categoryError.is(":hidden")) {
          categoryError.show();
        }
      }
    }

    return isCategorySelected();
  }

  //Stem 2 - enter bags quantity
  function validateBagsQuantity() {
    const ERROR = "Wprowadź ilość worków";
    const bagsNumbError = $(".bags_number-error");
    const $bagsNumbVal = $("#quantityInput").val();

    (function() {
      if (!isBagsNumbEntered()) {
        showErrorMessage();
      } else {
        bagsNumbError.hide();
      }
    })();

    function isBagsNumbEntered() {
      return $bagsNumbVal !== "" && typeof $bagsNumbVal !== 'undefined' &&
          $bagsNumbVal > 0;
    }

    function showErrorMessage() {
      if (bagsNumbError.length === 0) {
        let errorP = document.createElement("p");
        errorP.innerHTML = ERROR;
        errorP.classList.add("form-donation-error");
        errorP.classList.add("bags_number-error")
        $('.form--steps-counter').append(errorP);
      } else {
        if (bagsNumbError.is(":hidden")) {
          bagsNumbError.show();
        }
      }
    }

    return isBagsNumbEntered();
  }

  function validateFoundation() {
    const ERROR = "Wybierz jedną fundację";
    const foundationError = $(".foundation-error");

    (function () {
      if (!isFoundationSelected()) {
        showErrorMessage();
      } else {
        foundationError.hide();
      }
    })();

    function isFoundationSelected() {
      return selectedFoundation !== null
    }

    function showErrorMessage() {
      if (foundationError.length === 0) {
        let errorP = document.createElement("p");
        errorP.innerHTML = ERROR;
        errorP.classList.add("form-donation-error");
        errorP.classList.add("foundation-error")
        $('.form--steps-counter').append(errorP);
      } else {
        if (foundationError.is(":hidden")) {
          foundationError.show();
        }
      }
    }
    return isFoundationSelected();
  }

  function validateDeliveryDetails() {
    const ERROR = "Wypełnij dokładnie informacje dotyczące adresu oraz terminu odbioru";
    const EMPTY_ERROR = "pole nie może być puste";
    const HAS_DIGITS_ERROR = "pole nie może zawierać cyfr";
    const ZIPCODE_ERROR = "nieprawidłowy kod pocztowy";
    const PHONE_ERROR = "nieprawidłowy numer telefonu"
    const PAST_DATE_ERROR = "Wprowadzona data jest w podana czasie przeszłym." +
        " Taka operacja jest stosowana dla archiwizacji."
    const fieldsArray = [$streetInput, $cityInput, $zipCodeInput, $phoneInput,
          $dateInput, $hourInput];

    const deliveryDetailsError = $(".delivery_details-error");

    (function () {
      if (!isDeliveryDetailsCorrect()) {
        showErrorMessage();
      } else {
        deliveryDetailsError.hide();
      }
    })();

    function isDeliveryDetailsCorrect() {
      return checkErrorsInFields();
    }

    function showErrorMessage() {
      if (deliveryDetailsError.length === 0) {
        let errorP = document.createElement("p");
        errorP.innerHTML = ERROR;
        errorP.classList.add("form-donation-error");
        errorP.classList.add("delivery_details-error")
        $('.form--steps-counter').append(errorP);
      } else {
        if (deliveryDetailsError.is(":hidden")) {
          deliveryDetailsError.show();
        }
      }
    }

    function checkErrorsInFields() {
      let isCorrect = checkEmptyFields(fieldsArray);
      checkDate($dateInput.val())
      setSelectedError([$cityInput], HAS_DIGITS_ERROR,
          function hasDigit($inputValue) {
            const pattern = /\d+/g;
            let hasDigit = pattern.test($inputValue);
            isCorrect = hasDigit ? !isCorrect : isCorrect;
            return hasDigit;
          })
      setSelectedError([$zipCodeInput], ZIPCODE_ERROR,
          function matchingZipCode($inputVal) {
            const patternZipCode = /[0-9]{2}-[0-9]{3}/g;
            let isZipCodeMatching = !patternZipCode.test($inputVal);
            isCorrect = isZipCodeMatching ? !isCorrect : isCorrect;
            return isZipCodeMatching;
          })
      setSelectedError([$phoneInput], PHONE_ERROR,
          function matchingPhone($inputVal) {
            const pattern = /^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\\./0-9]*$/;
            let isPhoneCorrect = !pattern.test($inputVal);
            isCorrect = isPhoneCorrect ? !isCorrect : isCorrect;
            return isPhoneCorrect;
          })
      return isCorrect;
    }

    function setSelectedError(fieldsArr, errorToDisplay, searchErrorFunction) {
      fieldsArr.filter(field => {
        const fieldVal = field.val().trim();
        if (fieldVal !== "" && searchErrorFunction(fieldVal)) {
          field.val("");
          field.attr("placeholder", errorToDisplay).addClass("placeholder-error");
          field.css("background-color", "#ffcccc");
        } else if (isEmpty(fieldVal)){
          field.attr("placeholder", EMPTY_ERROR).addClass("placeholder-error");
          field.css("background-color", "#ffcccc");
        } else {
          field.css("background-color", "transparent");
        }
      })
    }

    function checkEmptyFields(fieldsArr) {
      let areNotEmpty = true;
      fieldsArr.filter(field => {
        if (isEmpty(field.val())) {
          field.val("");
          field.attr("placeholder", EMPTY_ERROR).addClass("placeholder-error");
          field.css("background-color", "#ffcccc");
          areNotEmpty = false;
        } else {
          field.css("background-color", "transparent");
        }
      })
      return areNotEmpty;
    }

    function checkDate(date) {
      const currentDate = new Date();
      const selectedDate = Date.parse(date);
      if(typeof selectedDate !== "undefined" && selectedDate-currentDate < 0) {
        alert(PAST_DATE_ERROR);
      }
    }

    function isEmpty($inputValue) {
      return $inputValue === "" || typeof $inputValue === "undefined" ||
          $inputValue === null;
    }

    return isDeliveryDetailsCorrect();
  }

});