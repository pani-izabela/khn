package application.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HelloController {

    public String showHello(){
        return "helloFromMenagedBean";
    }
}
