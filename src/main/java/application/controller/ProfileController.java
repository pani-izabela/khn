package application.controller;

import application.model.AppUser;
import application.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "KHN profile API controller")
@Controller
public class ProfileController {
    private AppUserService appUserService;

    public ProfileController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping(value = "*/profile")
    public String auctionsCustomer() {
        return "profile";
    }

    @ApiOperation(value = "Change data of user", response = AppUser.class)
    @PostMapping(value = "/*/changeDataAppUser")
    public @ResponseBody ResponseEntity<String> changeDataAppUser(@RequestBody AppUser newAppUser){
        if(appUserService.changeDataOfUser(newAppUser) != null){
            return new ResponseEntity<>("Dane zostały zmienione", HttpStatus.OK);
        }
        return new ResponseEntity<>("Nie udało się zmienić danych", HttpStatus.NOT_FOUND);
    }
}
