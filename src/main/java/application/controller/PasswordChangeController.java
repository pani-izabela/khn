package application.controller;

import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class PasswordChangeController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping(value = "/passwordChange")
    public String passwordReset() {
        return "passwordChange";
    }

    /*@PostMapping(value = "/userChangePassword")
    public @ResponseBody AppUser userChangePassword(@RequestBody AppUser appUser){
        return appUserService.
    }*/
}
