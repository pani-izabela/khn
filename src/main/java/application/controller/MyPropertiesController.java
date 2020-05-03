package application.controller;

import application.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyPropertiesController {

    @GetMapping(value = "/customer/myProperties")
    public String getMyProperties() {
        return "customer/myProperties";
    }

}
