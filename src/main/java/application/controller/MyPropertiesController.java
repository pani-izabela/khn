package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPropertiesController {

    @GetMapping(value = "/customer/myProperties")
    public String getMyProperties() {
        return "customer/myProperties3.xhtml";
    }

}
