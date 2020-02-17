package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppUserController {


    private AppUserService appUserService;

    private AppUserDAO appUserDAO;

    public AppUserController(AppUserService appUserService, AppUserDAO appUserDAO) {
        this.appUserService = appUserService;
        this.appUserDAO = appUserDAO;
    }

//    public AppUserController(AppUserService appUserService){
//        this.appUserService = appUserService;
//    }

    //-----------------------------------------------------------

    @GetMapping(value = "/getAppUsers")
    public List<AppUser> getAppUsers() {
        return appUserService.findAllQuery();
    }

    @GetMapping(value = "/getAppUser")
    public AppUser getAppUser(@RequestParam int id){
        return appUserDAO.findById(id);
    }

    /*@PostMapping(value = "/addAppUser")
    public AppUser addAppUser(@RequestBody AppUser appUser){
        return appUserDAO.addAppUser(appUser);
    }*/

    @DeleteMapping(value = "/deleteAppUser")
    public void deleteAppUser(@RequestParam int id){
        appUserDAO.deleteById(id);
    }
}
