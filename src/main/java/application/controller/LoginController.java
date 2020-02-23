package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody AppUser loginUser(@RequestBody AppUser appUser){
        String email = appUser.getEmail();
        String pass = appUser.getPass();
        AppUser appUserFromDb = appUserService.findAppUserByEmailAndPass(email, pass);
        return appUserFromDb;
    }

}
