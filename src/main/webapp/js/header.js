$(document).ready(function () {
    toggleClassSidebar();
    //showEmailLoggedUser();
});

function toggleClassSidebar() {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
}
function showEmailLoggedUser() {
    function createBodyElement(name) {
        let input = document.createElement("SPAN");
        input.textContent = name;
        input.setAttribute('class', 'nav');
        return input;
    }
    const a = document.querySelector('#placeForEmail');
    a.appendChild(createBodyElement(localStorage.getItem('loggedUserEmail')));
}