$(document).ready(function(){
   $("#footerOnPage").load("/footer.html");
   $("#headerOnCustomerPage").load("/customer/header.html");
   $("#headerOnSellerPage").load("/seller/header.html");
   $("#sidebarOnCustomerPage").load("/customer/sidebar.html");
   $("#sidebarOnSellerPage").load("/seller/sidebar.html");

   /*$('#sidebarCollapse').on('click', function () {
      $('#sidebar').toggleClass('active');
   });*/
   showEmailLoggedUser();
});

/*
function showEmailLoggedUser() {
   function createBodyElement(name) {
      let input = document.createElement("SPAN");
      input.textContent = name;
      return input;
   }
   const a = document.querySelector('#placeForEmail');
   a.appendChild(createBodyElement(localStorage.getItem('loggedUserEmail')));
}*/
