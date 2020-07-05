
package application.bean;

import application.model.AuctionView;
import application.service.AppUserService;
import application.service.FinanceService;
import application.service.MyPropertiesService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private double amountPLN;
    BigDecimal euro = new BigDecimal("4.46");
    BigDecimal dollar = new BigDecimal("3.96");

    @PostConstruct
    public void getMyProperties(){
        myPropertiesList = myPropertiesService.getPropertiesForCustomer(appUserService.getLoggedCustomerId());
    }

    public double getAmount(){
        amountPLN = financeService.getAmountByAppUserId(appUserService.getLoggedCustomerId());
        return amountPLN;
    }

    public double getAmountEuro(){
        BigDecimal pln = BigDecimal.valueOf(amountPLN);
        BigDecimal eur = pln.divide(euro,2, RoundingMode.HALF_UP);
        return eur.doubleValue();
    }

    public double getAmountDollar(){
        BigDecimal pln = BigDecimal.valueOf(amountPLN);
        BigDecimal usd = pln.divide(dollar,2, RoundingMode.HALF_UP);
        return usd.doubleValue();
    }

    public String getColorOfFrameCustomer(){
        String color;
        if(financeService.getAmountByAppUserId(appUserService.getLoggedCustomerId())>=0.0){
            color = "GREEN";
        }
        else {
            color =  "CORAL";
        }
        return color;
    }

}

