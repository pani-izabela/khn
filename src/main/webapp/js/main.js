$(document).ready(function(){
   $("#footerOnPage").load("/footer.html");
   $("#headerOnCustomerPage").load("/customer/header.html");
   $("#headerOnSellerPage").load("/seller/header.html");
   $("#sidebarOnCustomerPage").load("/customer/sidebar.html");
   $("#sidebarOnSellerPage").load("/seller/sidebar.html");

   showEmailLoggedUser();
});

