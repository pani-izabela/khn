package application.controller;

import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordChangeController {
    private final AppUserService appUserService;

    public PasswordChangeController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping(value = "/passwordChange")
    public String passwordReset() {
        return "passwordChange";
    }

    @PostMapping(value = "/userChangePassword")
    public @ResponseBody ResponseEntity<String> userChangePassword(String emailField, String oldPassField, String newPassField){
        return appUserService.changePassword(emailField, oldPassField, newPassField);
    }
}
