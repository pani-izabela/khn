package application.controller;

import application.model.AppUser;
import application.model.ChangePasswordData;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordChangeController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping(value = "/passwordChange")
    public String passwordReset() {
        return "passwordChange";
    }

    @PutMapping(value = "/userChangePassword")
    public @ResponseBody AppUser userChangePassword(@RequestBody ChangePasswordData changePasswordData){
        String email = changePasswordData.getEmail();
        String oldPass = changePasswordData.getOldPass();
        String newPass = changePasswordData.getNewPass();
        AppUser appUserFromDb = appUserService.findAppUserByEmailAndPass(email, oldPass);
        AppUser appUserToReturn = new AppUser();
        if(appUserFromDb!=null && (newPass.equals(oldPass)==false)){
            appUserToReturn = appUserService.updatePass(appUserFromDb, newPass);
        }

        return appUserToReturn;
    }
}
