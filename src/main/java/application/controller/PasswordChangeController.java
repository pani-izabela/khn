package application.controller;

import application.model.AppUser;
import application.service.AppUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
        return "passwordChange.html";
    }

    @ApiOperation(value = "Change data of user")
    @ApiImplicitParams({
            @ApiImplicitParam(name="emailField", value = "AppUser email", dataType = "String", required = true),
            @ApiImplicitParam(name="oldPassField", value = "AppUser pass", dataType = "String", required = true),
            @ApiImplicitParam(name="newPassField", value = "AppUser new pass", dataType = "String", required = true)})
    @PostMapping(value = "/userChangePassword")
    public @ResponseBody ResponseEntity<String> userChangePassword(String emailField, String oldPassField, String newPassField){
        if(appUserService.changePassword(emailField, oldPassField, newPassField)!=null)
            return new ResponseEntity<>("Hasło zostało zmienione", HttpStatus.OK);

        return new ResponseEntity<>("Nie udało się zmienić hasła, sprawdź poprawność danych", HttpStatus.NOT_FOUND);
    }
}
