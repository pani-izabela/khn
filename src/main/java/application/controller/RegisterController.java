package application.controller;

import application.model.AppUser;
import application.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "KHN register API controller")
@Controller
public class RegisterController {

    private AppUserService appUserService;

    public RegisterController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @ApiOperation(value = "Show registration view for customer")
    @GetMapping(value = "/customer/register")
    public String registerCustomer() {
        return "customer/register";
    }

    @ApiOperation(value = "Show registration view for seller")
    @GetMapping(value = "/seller/register")
    public String registerSeller() {
        return "seller/register";
    }

    @ApiOperation(value = "Add new customer to application", response = AppUser.class)
    @PostMapping(value = "/customer/addAppUser")
    public @ResponseBody AppUser addAppUser(@RequestBody AppUser appUser){
        return appUserService.registerCustomerUser(appUser);
    }

    @ApiOperation(value = "Add new seller to application", response = AppUser.class)
    @PostMapping(value = "/seller/addAppUser")
    public @ResponseBody AppUser addAppUserSeller(@RequestBody AppUser appUser){
        return appUserService.registerSellerUser(appUser);
    }

}
