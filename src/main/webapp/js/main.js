$(document).ready(function(){
   $("#footerOnPage").load("/footer.html");
   $("#headerOnCustomerPage").load("/customer/header.html");
   $("#headerOnSellerPage").load("/seller/header.html");
   $("#sidebarOnPage").load("/customer/sidebar.html");
});

$(document).ready(function () {
   $('#sidebarCollapse').on('click', function () {
      $('#sidebar').toggleClass('active');
   });
});