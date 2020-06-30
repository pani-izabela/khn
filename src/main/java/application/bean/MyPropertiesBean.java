
package application.bean;

import application.model.AuctionView;
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

    private List<AuctionView> list;

    @PostConstruct
    public void getAllAuctionViews(){
        list = myPropertiesService.getAllProperties();
    }
}

