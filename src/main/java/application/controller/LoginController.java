package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.model.Role;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class LoginController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserDAO appUserDAO;

    @GetMapping(value = "/customer/login")
    public String loginCustomer() {
        return "customer/login";
    }

    @GetMapping(value = "/seller/login")
    public String loginSeller() {
        return "seller/login";
    }

    @PostMapping(value = "/customer/loginUser")
    public @ResponseBody AppUser loginCustomerUser(@RequestBody AppUser appUser){
        String email = appUser.getEmail();
        String pass = appUser.getPass();

        AppUser appUserFromDb = appUserService.findAppUserByEmailAndPass(email, pass);
        AppUser appUserToReturn = new AppUser();

        if(appUserFromDb!=null){
            int usersRoleId = 0;

            List<Role> userRoles = appUserFromDb.getRoles();
            for (Role role : userRoles) {
                usersRoleId = role.getId();
            }

            if ((usersRoleId == 1) || (usersRoleId == 3))
                appUserToReturn = appUserFromDb;
        }
        return appUserToReturn;
    }

    @PostMapping(value = "/seller/loginUser")
    public @ResponseBody AppUser loginSellerUser(@RequestBody AppUser appUser){
        String email = appUser.getEmail();
        String pass = appUser.getPass();

        AppUser appUserFromDb = appUserService.findAppUserByEmailAndPass(email, pass);
        AppUser appUserToReturn = null;

        if(appUserFromDb!=null){
            int usersRoleId = 0;

            List<Role> userRoles = appUserFromDb.getRoles();
            for (Role role : userRoles) {
                usersRoleId = role.getId();
            }

            if ((usersRoleId == 2) || (usersRoleId == 3))
                appUserToReturn = appUserFromDb;
        }
        return appUserToReturn;
    }

}
