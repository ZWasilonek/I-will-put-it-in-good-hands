document.addEventListener("DOMContentLoaded", function() {

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
  document.addEventListener("click", function(e) {
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

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
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

  const categoryIdInputArray = [];
  $('#donationForm .categoryIdInput').on('change', function() {

    $('input[class = categoryIdInput]:checked').each(function () {
      const categoryID = $(this).val();
      if (categoryIdInputArray.indexOf(categoryID) === -1) {
        categoryIdInputArray.push(categoryID);
      }
    });
  });

  const insertFoundationDescriptionHTML = function (categoryIdArr) {
    categoryIdArr.forEach((id, index) => {
      if (categoriesName.get(id)) {
        const spamItem = categoriesName.get(id);
        $("#categoryName").append((index === categoryIdArr.length-1) ? spamItem : spamItem + ", ");
      }
    })
  }

  $("#quantityInput").on('input', function() {
    let quantityVal = $(this).val();
    $("#quantity").text(quantityVal);

    switch (quantityVal) {
      case "1":
        $("#bagsNumber").text(bagNumber[0]);
        insertFoundationDescriptionHTML(categoryIdInputArray);
        break;
      case "2":
      case "3":
      case "4":
        $("#bagsNumber").text(bagNumber[1]);
        insertFoundationDescriptionHTML(categoryIdInputArray);
        break;
      default:
        $("#bagsNumber").text(bagNumber[2]);
        insertFoundationDescriptionHTML(categoryIdInputArray);
        break;
    }
  });

  /**
   * Update form front-end
   * Show institution name and city.
   */
  const institutionsNamesMap = new Map();
  institutionsNamesMap.set('1','Fundacji “Dbam o Zdrowie”');
  institutionsNamesMap.set('2','Fundacji “A kogo”');
  institutionsNamesMap.set('3','Fundacji “Dla dzieci”');
  institutionsNamesMap.set('4','Fundacji “Bez domu”');

  $('#donationForm .foundationNameInput').on('change', function() {
    const selectedInstitutionRadioId = $('input[class=foundationNameInput]:checked').val();
    for (let [key] of institutionsNamesMap) {
      if (selectedInstitutionRadioId === key) {
        $("#foundationName").text(institutionsNamesMap.get(key));
        break;
      }
    }
  });

  $("#foundationCityInput").on("change paste keyup", function() {
    foundationCityValue = $(this).val();
    $("#foundationCity").text(foundationCityValue);
  });

  /**
   * Update form front-end
   * Show pickup address.
   */
  $("#streetInput").on("change paste keyup", function() {
    streetValue = $(this).val();
    $("#streetLi").text(streetValue);
  });

  $("#cityInput").on("change paste keyup", function() {
    cityVal = $(this).val();
    $("#cityLi").text(cityVal);
  });

  $("#zipCodeInput").on("change paste keyup", function() {
    zipCodeVal = $(this).val();
    $("#zipCodeLi").text(zipCodeVal);
  });

  $("#phoneInput").on("change paste keyup", function() {
    phoneVal = $(this).val();
    $("#phoneLi").text(phoneVal);
  });

  /**
   * Update form front-end
   * Show pickup date.
   */
  $("#dateInput").on("change paste keyup", function() {
    dateVal = $(this).val();
    $("#dateLi").text(dateVal);
  });

  $("#hourInput").on("change paste keyup", function() {
    hourVal = $(this).val();
    $("#hourLi").text(hourVal);
  });

  $("#commentsTextarea").on("change paste keyup", function() {
    commentsVal = $(this).val();
    if (commentsVal !== '') {
      $("#commentsLi").text(commentsVal);
    }
  });

  /**
   * Update form front-end
   * Hide input from 1-form donation.
   */
  $("#donationForm").append("<input type='hidden' value=on>");
  $("input[type='hidden']").remove();


  /**
   * Update form front-end
   * Spring Security Configuration using JSON
   */
  // var token = $("meta[name='_csrf']").attr("content");
  // var header = $("meta[name='_csrf_header']").attr("content");
  //
  // $(document).ajaxSend(function(e, xhr, options) {
  //   xhr.setRequestHeader(header, token);
  // });
});
