package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/index")
    public String index() {
        return "index.html";
    }

    @GetMapping(value = "*/menu")
    public String adminMenu() {
        return "menu.html";
    }

    @GetMapping(value="/footer")
    public String footer(){
        return "footer.html";
    }

    @GetMapping(value="/header")
    public String header(){
        return "header.html";
    }

    @GetMapping(value="/sidebar")
    public String sidebar(){
        return "sidebar.html";
    }


}
