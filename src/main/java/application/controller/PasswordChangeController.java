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
        //Tak samo jak w kontrolerze do logowania, wyniesc to wszystko ponizej do serwisu
        // Przykladowo jedna metoda w serwisie do zmiany hasla, ktora bedzie posiadac prywatna metode do sprawdzenia czy user istnieje w bazie oraz kolejna prywatna metode do zmiany hasla
        // Dodatkowo tutaj nie musi nam zopstac zwrocony uzytkownik, mozesz poczytac  np o ResponseEntity albo zwrocic boolean :)

        String email = changePasswordData.getEmail();
        String oldPass = changePasswordData.getOldPass();
        String newPass = changePasswordData.getNewPass();
        AppUser appUserFromDb = appUserService.findAppUserByEmailAndPass(email, oldPass);
        AppUser appUserToReturn;
        if(appUserFromDb!=null && (newPass.equals(oldPass)==false)){
            appUserToReturn = appUserService.updatePass(appUserFromDb, newPass);
        }
        else
            appUserToReturn = null;
        return appUserToReturn;
    }
}
