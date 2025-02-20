// Hamburger menü megjelenítése és elrejtése
const hamburgerIcon = document.getElementById("hamburger-icon");
const hamburgerMenu = document.getElementById("hamburger-menu");

hamburgerIcon.addEventListener("click", function() {
    hamburgerMenu.style.display = hamburgerMenu.style.display === "block" ? "none" : "block";
});

// Termék eltávolítása
function removeItem(button) {
    const item = button.closest(".cart-item");
    item.remove();
    updateSubtotal();
}

// Részösszeg frissítése
function updateSubtotal() {
    const prices = document.querySelectorAll(".cart-item .item-details p:nth-child(2)");
    let total = 0;
    prices.forEach(price => {
        total += parseInt(price.textContent.replace(/[^\d]/g, ""));
    });
    document.getElementById("subtotal").textContent = `${total.toLocaleString()} Ft`;
}

// Lépések közötti navigáció
function goToStep(stepNumber) {
    const steps = document.querySelectorAll(".step");
    steps.forEach((step, index) => {
        step.classList.toggle("active", index + 1 === stepNumber);
    });
}

// Vásárlás véglegesítése
function completePurchase(event) {
    event.preventDefault();  // Ne küldje el az űrlapot
    // Sikeres vásárlás üzenet megjelenítése
    goToStep(4);  // Átváltunk a sikeres vásárlás lépésére
}

// Visszairányítás a főoldalra
function redirectToHome() {
    window.location.href = "webshop_home_page.html";
}
