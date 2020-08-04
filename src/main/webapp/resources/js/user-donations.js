document.addEventListener("DOMContentLoaded", function() {
    /**
     * Show user donations
     */
    $('li.showUserContent').click(function () {
        let id = $(this).attr('rel');
        let selectedContent = $('#' + id).slideToggle('slow');
        $('h1.hideUserNameH1').replaceWith(selectedContent);
    })

    /** Categories **/
    const categoriesNamePattern =
        ["ubrania, które nadają się do ponownego użycia", "ubrania do wyrzucenia", "zabawki", "książki", "inne"]
    const categoriesName =
        ["ubrań w dobrym stanie dla dzieci", "ubrań do wyrzucenia", "zabawek", "książek", "innych"]
    let categories = $(".categoryName");

    categories.map((index) => {
        let categoryName = categories[index];
        let checkCategoryName = categoryName.innerText.trim().replace(new RegExp("," + "$"), "");
        for (let i = 0; i < categoriesNamePattern.length; i++) {
            if (checkCategoryName === categoriesNamePattern[i]) {
                categoryName.innerText = categoriesName[i] + ", ";
            }
        }
    })

    /** Bags quantity **/
    const bagNumber = ["worek", "worki", "worków"];
    let bagsQuantity = $(".quantityBags");
    let $bags = $(".bags");

    bagsQuantity.map((index) => {
        let bagWord = $bags[index];
        let bagNum = bagsQuantity[index].innerText;
        switch (bagNum) {
            case "1":
                bagNum = bagNumber[0];
                bagWord.innerText = bagNum
                break;
            case "2":
            case "3":
            case "4":
                bagNum = bagNumber[1];
                bagWord.innerText = bagNum
                break;
            default:
                bagNum = bagNumber[2];
                bagWord.innerText = bagNum
                break;
        }
    });
});

