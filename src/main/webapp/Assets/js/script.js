document.addEventListener("DOMContentLoaded", function() {
    // Toggle the hamburger menu
	window.toggleMenu = function() {
	        const menu = document.querySelector('.navbar .menu');
	        menu.classList.toggle('active');
	    };

	    // Function to toggle the dropdown menu on Home button
	    window.toggleHomeMenu = function(event) {
	        event.stopPropagation();
	        const homeMenu = document.querySelector(".navbar .menu li .dropdown");
	        homeMenu.style.display = homeMenu.style.display === "block" ? "none" : "block";
	    };

	    // Close the dropdown when clicking outside
	    document.addEventListener("click", function() {
	        const homeMenu = document.querySelector(".navbar .menu li .dropdown");
	        homeMenu.style.display = "none"; // Close the dropdown menu
	    });

    // Functions to show and close forms
    function showForm(formId) {
        document.querySelectorAll('.form-container').forEach(function(form) {
            form.style.visibility = 'hidden';
            form.style.display = 'none';
        });
        const form = document.getElementById(formId);
        form.style.visibility = 'visible';
        form.style.display = 'flex';
    }

    function closeForm(formId) {
        const form = document.getElementById(formId);
        form.style.visibility = 'hidden';
        form.style.display = 'none';
    }

    // Exposing functions globally
    window.showForm = showForm;
    window.closeForm = closeForm;
});
