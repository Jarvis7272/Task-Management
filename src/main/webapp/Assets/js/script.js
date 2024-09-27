document.addEventListener("DOMContentLoaded", function() {
    function toggleHomeMenu(event) {
        event.stopPropagation();
        var homeMenu = document.querySelector(".navbar .menu li .dropdown");
        homeMenu.style.display = homeMenu.style.display === "block" ? "none" : "block";
    }

    function toggleMenu() {
        var menu = document.querySelector(".navbar .menu");
        menu.classList.toggle("active");
    }

    // Close dropdown if clicking outside
    document.addEventListener("click", function() {
        var homeMenu = document.querySelector(".navbar .menu li .dropdown");
        homeMenu.style.display = "none";
    });

    function showForm(formId) {
        // Hide all forms
        document.querySelectorAll('.form-container').forEach(function(form) {
            form.style.visibility = 'hidden';
            form.style.display = 'none';
        });
        // Show the selected form
        const form = document.getElementById(formId);
        form.style.visibility = 'visible';
        form.style.display = 'flex';
    }

    function closeForm(formId) {
        const form = document.getElementById(formId);
        form.style.visibility = 'hidden';
        form.style.display = 'none';
    }

    // Make functions accessible globally if needed
    window.showForm = showForm;
    window.closeForm = closeForm;
    window.toggleHomeMenu = toggleHomeMenu;
    window.toggleMenu = toggleMenu;
});
