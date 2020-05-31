package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddPropertyController {

    @GetMapping(value = "/seller/addProperty")
    public String getMyProperties() {
        return "seller/addProperty";
    }

}
