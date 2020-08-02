package application.bean;

import application.model.AppUser;
import application.service.AppUserService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class CreditFormBean {

    @ManagedProperty("#{appUserService}")
    private AppUserService appUserService;

    private String name;
    private String surname;

    public String getAppUserName(){
        int appUserId = appUserService.getLoggedCustomerId();
        AppUser appUser = appUserService.findByIdQuery(appUserId);
        return name = appUser.getFirstname();
    }

    public String getAppUserSurname(){
        int appUserId = appUserService.getLoggedCustomerId();
        AppUser appUser = appUserService.findByIdQuery(appUserId);
        return surname = appUser.getLastname();
    }
}
