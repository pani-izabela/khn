package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AppUserController {

    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    //-----------------------------------------------------------

    @GetMapping(value = "/getAppUsers")
    public @ResponseBody List<AppUser> getAppUsers() {
        return appUserService.findAllQuery();
    }
}
