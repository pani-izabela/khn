package application.controller;

import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    //elegancko posprzatane [ w sumie jak i w  loginController] :)
    @PostMapping(value = "/userChangePassword")
    public @ResponseBody ResponseEntity<String> userChangePassword(String emailField, String oldPassField, String newPassField){
        if(appUserService.changePassword(emailField, oldPassField, newPassField)!=null)
            return new ResponseEntity<>("Hasło zostało zmienione", HttpStatus.OK);

        return new ResponseEntity<>("Nie udało się zmienić hasła, sprawdź poprawność danych", HttpStatus.NOT_FOUND);
    }
}
