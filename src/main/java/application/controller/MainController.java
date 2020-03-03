package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/customer/menu")
    public String customerMenu() {
        return "customer/menu";
    }

    @GetMapping(value = "/seller/menu")
    public String sellerMenu() {
        return "seller/menu";
    }
}
