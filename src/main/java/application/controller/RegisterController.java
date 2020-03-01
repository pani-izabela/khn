package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.model.Role;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping(value = "/customer/register")
    public String registerCustomer() {
        return "customer/register";
    }

    @GetMapping(value = "/seller/register")
    public String registerSeller() {
        return "seller/register";
    }

    @PostMapping(value = "/customer/addAppUser")
    public @ResponseBody AppUser addAppUser(@RequestBody AppUser appUser){
        return appUserService.addAppUser(appUser, "customer");
    }
    @PostMapping(value = "/seller/addAppUser")
    public @ResponseBody AppUser addAppUserSeller(@RequestBody AppUser appUser){
        return appUserService.addAppUser(appUser, "seller");
    }

}
