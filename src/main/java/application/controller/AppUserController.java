package application.controller;

import application.dao.AppUserDAO;
import application.model.AppUser;
import application.service.AppUserService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "KHN user API controller")
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

    @ApiOperation(value = "Get all users")
    @GetMapping(value = "/getAppUsers")
    public @ResponseBody List<AppUser> getAppUsers() {
        return appUserService.findAllQuery();
    }

    @GetMapping(value = "/getAppUser")
    public @ResponseBody AppUser getAppUser(@RequestBody @RequestParam int id){
        return appUserDAO.findById(id);
    }

    @ApiOperation(value = "Delete user by id")
    @ApiImplicitParam(name="id", value = "User id", required = true)
    @DeleteMapping(value = "/deleteAppUser")
    public @ResponseBody ResponseEntity<String> deleteAppUser(@RequestBody @RequestParam int id, @RequestParam int loggedUserId){
        if(loggedUserId!=id) {
            appUserService.deleteAppUser(id, loggedUserId);
            return new ResponseEntity<>("Użytkownik został usunięty", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Brak uprawnień do wykonania akcji", HttpStatus.NOT_FOUND);
        }
    }
}
