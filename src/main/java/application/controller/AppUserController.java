package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserDAO appUserDAO;

//    public AppUserController(AppUserService appUserService){
//        this.appUserService = appUserService;
//    }

    //-----------------------------------------------------------

    @GetMapping(value = "/getAppUsers")
    public List<AppUser> getAppUsers() {
        return appUserService.findAllQuery();
    }

    @PostMapping(value = "/addAppUser")
    public AppUser addAppUser(@RequestBody AppUser appUser){
        return appUserDAO.addAppUser(appUser);
    }
}
