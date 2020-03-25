package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
public class AppUserController {


    private AppUserService appUserService;

    private AppUserDAO appUserDAO;

    public AppUserController(AppUserService appUserService, AppUserDAO appUserDAO) {
        this.appUserService = appUserService;
        this.appUserDAO = appUserDAO;
    }

    //-----------------------------------------------------------

    @GetMapping(value="/admin/users")
    public String adminUsers(){
        return "admin/users";
    }

    @GetMapping(value = "/getAppUsers")
    public @ResponseBody List<AppUser> getAppUsers() {
        return appUserService.findAllQuery();
    }

    @GetMapping(value = "/getAppUser")
    public @ResponseBody AppUser getAppUser(@RequestBody @RequestParam int id){
        return appUserDAO.findById(id);
    }

    /*@PostMapping(value = "/addAppUser")
    public AppUser addAppUser(@RequestBody AppUser appUser){
        return appUserDAO.addAppUser(appUser);
    }*/

    @DeleteMapping(value = "/deleteAppUser")
    public @ResponseBody void deleteAppUser(@RequestBody @RequestParam int id){
        appUserDAO.deleteById(id);
    }
}
