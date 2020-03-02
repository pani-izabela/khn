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


    // przez konstruktor :)
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
        // do serwisu przeslalabym calego AppUser i dopiero tam pobierala wartosci, ktorych potrzebujemy
        String email = appUser.getEmail();
        String pass = appUser.getPass();
        return appUserService.loginUserFromCustomerPage(email, pass);
    }

    @PostMapping(value = "/seller/loginUser")
    public @ResponseBody AppUser loginSellerUser(@RequestBody AppUser appUser){
        // do serwisu przeslalabym calego AppUser i dopiero tam pobierala wartosci, ktorych potrzebujemy
        String email = appUser.getEmail();
        String pass = appUser.getPass();
        return appUserService.loginUserFromSellerPage(email, pass);
    }

}
