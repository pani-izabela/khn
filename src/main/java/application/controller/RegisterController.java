package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserDAO appUserDAO;

    @GetMapping(value = "/customer/register")
    public String registerCustomer() {
        return "customer/register";
    }

    @GetMapping(value = "/seller/register")
    public String registerSeller() {
        return "seller/register";
    }

    @PostMapping(value = "/addAppUser")
    public @ResponseBody AppUser addAppUser(@RequestBody AppUser appUser){
        return appUserService.addAppUser(appUser);
    }


}
