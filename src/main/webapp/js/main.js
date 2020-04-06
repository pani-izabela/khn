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
   var isAdmin, isSeller, isCustomer;
   var rolesArray = data["roles"];
   for (let value of Object.values(rolesArray)) {
      console.log(value.id);
      role = value.id;
      if (role === 1)
         isCustomer = true;
      else if (role === 2)
         isSeller = true;
      else if (role === 3)
         isAdmin = true;
   }
   if (isAdmin === true) {
      return "admin";
   } else {
      if (isSeller === true && isCustomer === true)
         return "customer+seller";
      else if (isSeller === true)
         return "seller";
      else if (isCustomer === true)
         return "customer";
   }
}

function logout(page) {
   $.ajax({
      url: "http://localhost:8080/performLogout",
      type: "GET",
      contentType: "application/json",
   });
   if (page === 'admin')
      window.location = "http://localhost:8080/index";
   else {
      window.location = "login";
   }
}

