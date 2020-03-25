$(document).ready(function(){
   $("#footerOnPage").load("/footer.html");
   $("#headerOnCustomerPage").load("/customer/header.html");
   $("#headerOnSellerPage").load("/seller/header.html");
   $("#headerOnAdminPage").load("/admin/header.html");
   $("#sidebarOnCustomerPage").load("/customer/sidebar.html");
   $("#sidebarOnSellerPage").load("/seller/sidebar.html");
   $("#sidebarOnAdminPage").load("/admin/sidebar.html");

   showEmailLoggedUser();
   logout();
});

function setRole() {
   if ((window.location.href).includes('customer')){
      return 'customer'
   }
   else if((window.location.href).includes('seller')){
      return 'seller'
   }
}

function checkRole(data) {
   var role;
   var rolesArray = data["roles"];
   for (let value of Object.values(rolesArray)) {
      console.log(value.id);
      role = value.id;
      if(role===1 || role ===2){
         return "usualUser"
      }
      else if(role===3){
         return "admin"
      }
   }
}

function logout() {
   $('#logout').on('click', function () {
      window.location.href = "login";
   });
}

