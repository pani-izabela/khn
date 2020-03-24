$(document).ready(function(){
   $("#footerOnPage").load("/footer.html");
   $("#headerOnCustomerPage").load("/customer/header.html");
   $("#headerOnSellerPage").load("/seller/header.html");
   $("#headerOnAdminPage").load("/admin/header.html");
   $("#sidebarOnCustomerPage").load("/customer/sidebar.html");
   $("#sidebarOnSellerPage").load("/seller/sidebar.html");
   $("#sidebarOnAdminPage").load("/admin/sidebar.html");

   showEmailLoggedUser();
});

function setRole() {
   if ((window.location.href).includes('customer')){
      return 'customer'
   }
   else if((window.location.href).includes('seller')){
      return 'seller'
   }
}

