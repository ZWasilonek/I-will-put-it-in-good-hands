document.addEventListener("DOMContentLoaded", function() {
    const $removeUserBtn = document.getElementById("removeUserBtn");

    $removeUserBtn.addEventListener("click", () => {
        let answer = confirm(`Jesteś pewny/a, że chcesz usunąć konto ?`);
        if (answer) {
            $removeUserBtn.setAttribute("href", "/user/remove");
        }
    })
});