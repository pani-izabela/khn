package application.controller;

import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private AppUserService appUserService;

    public RegisterController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

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
        return appUserService.registerCustomerUser(appUser);
    }
    @PostMapping(value = "/seller/addAppUser")
    public @ResponseBody AppUser addAppUserSeller(@RequestBody AppUser appUser){
        return appUserService.registerSellerUser(appUser);
    }

}
