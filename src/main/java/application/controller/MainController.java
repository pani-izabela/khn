package application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @GetMapping(value="/footer")
    public String footer(){
        return "footer";
    }

    @GetMapping(value="/customer/header")
    public String headerCustomer(){
        return "customer/header";
    }

    @GetMapping(value="/seller/header")
    public String headerSeller(){
        return "seller/header";
    }

    @GetMapping(value="/customer/sidebar")
    public String sidebarCustomer(){
        return "customer/sidebar";
    }
}
