package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.model.Role;
import application.service.AppUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Api(value = "KHN login API controller")
@Controller
public class LoginController {

    private final AppUserService appUserService;

    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


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
        return appUserService.loginUserFromCustomerPage(appUser);
    }

    @PostMapping(value = "/seller/loginUser")
    public @ResponseBody AppUser loginSellerUser(@RequestBody AppUser appUser){
        return appUserService.loginUserFromSellerPage(appUser);
    }

}
