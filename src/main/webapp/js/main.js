$(document).ready(function(){
   $("#footerOnPage").load("/footer.html");
   $("#headerOnEveryPage").load("/header.html");
   $("#sidebarOnEveryPage").load("/sidebar.html");

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
   var isAdmin, isSeller, isCustomer, isCustomerAndSeller;
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
      localStorage.setItem('userIsAdmin', isAdmin);
      console.log('user is admin main ', localStorage.getItem('userIsAdmin'));
      return "admin";
   } else {
      if (isSeller === true && isCustomer === true){
         isCustomerAndSeller = true;
         localStorage.setItem('userIsCustomerAndSeller', isCustomerAndSeller);
         return "customer+seller";
      }
      else if (isSeller === true) {
         localStorage.setItem('userIsSeller', isSeller);
         return "seller";
      }
      else if (isCustomer === true) {
         localStorage.setItem('userIsCustomer', isCustomer);
         console.log('user is customer main ', localStorage.getItem('userIsCustomer'));
         return "customer";
      }
   }
}


function logout() {
   $.ajax({
      url: "http://localhost:8080/performLogout",
      type: "GET",
      contentType: "application/json",
   });
   window.location = "http://localhost:8080/index";
   localStorage.setItem('userIsAdmin', null);
   localStorage.setItem('userIsCustomerAndSeller', null);
   localStorage.setItem('userIsSeller', null);
   localStorage.setItem('userIsCustomer', null);
}

