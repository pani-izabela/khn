
package application.bean;

import application.model.AuctionView;
import application.service.AppUserService;
import application.service.MyPropertiesService;
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
public class PropertiesForSaleBean {

    @ManagedProperty("#{myPropertiesService}")
    private MyPropertiesService myPropertiesService;

    @ManagedProperty("#{appUserService}")
    private AppUserService appUserService;

    private List<AuctionView> propertiesForSaleList;

    @PostConstruct
    public void getPropertiesForSale(){
        propertiesForSaleList = myPropertiesService.getPropertiesForSeller(appUserService.getLoggedSellerId());
    }
}

