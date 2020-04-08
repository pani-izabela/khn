package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

   /* @GetMapping(value = "/customer/menu")
    public String customerMenu() {
        return "menu";
    }

    @GetMapping(value = "/seller/menu")
    public String sellerMenu() {
        return "/menu";
    }

    @GetMapping(value = "/admin/menu")
    public String adminMenu() {
        return "menu";
    }*/

    @GetMapping(value = "*/menu")
    public String adminMenu() {
        return "menu";
    }

    @GetMapping(value="/footer")
    public String footer(){
        return "footer";
    }

    @GetMapping(value="/header")
    public String header(){
        return "header";
    }

    @GetMapping(value="/sidebar")
    public String sidebar(){
        return "sidebar";
    }

    /*@GetMapping(value="/customer/header")
    public String headerCustomer(){
        return "customer/header";
    }

    @GetMapping(value="/seller/header")
    public String headerSeller(){
        return "seller/header";
    }

    @GetMapping(value="/admin/header")
    public String headerAdmin(){
        return "admin/header";
    }*/

   /* @GetMapping(value="/customer/sidebar")
    public String sidebarCustomer(){
        return "customer/sidebar";
    }

    @GetMapping(value="/seller/sidebar")
    public String sidebarSeller(){
        return "seller/sidebar";
    }

    @GetMapping(value="/admin/sidebar")
    public String sidebarAdmin(){
        return "admin/sidebar";
    }*/
}
