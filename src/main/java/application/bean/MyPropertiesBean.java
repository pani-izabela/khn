
package application.bean;

import application.model.AuctionView;
import application.service.AppUserService;
import application.service.FinanceService;
import application.service.MyPropertiesService;
import application.service.MyPropertiesServiceImpl;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class MyPropertiesBean {

    @ManagedProperty("#{myPropertiesService}")
    private MyPropertiesService myPropertiesService;

    @ManagedProperty("#{appUserService}")
    private AppUserService appUserService;

    @ManagedProperty("#{financeService}")
    private FinanceService financeService;

    private List<AuctionView> myPropertiesList;

    @PostConstruct
    public void getMyProperties(){
        myPropertiesList = myPropertiesService.getPropertiesForCustomer(appUserService.getLoggedCustomerId());
    }

    /*public double getAmount(){
        return financeService.getAmountByAppUserId(appUserService.getLoggedCustomerId());
    }*/

}

