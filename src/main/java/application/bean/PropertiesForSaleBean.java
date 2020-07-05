
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
public class PropertiesForSaleBean {

    @ManagedProperty("#{myPropertiesService}")
    private MyPropertiesService myPropertiesService;

    @ManagedProperty("#{appUserService}")
    private AppUserService appUserService;

    @ManagedProperty("#{financeService}")
    private FinanceService financeService;

    private List<AuctionView> propertiesForSaleList;
    private double amountPLNSeller;
    BigDecimal euro = new BigDecimal("4.46");
    BigDecimal dollar = new BigDecimal("3.96");

    @PostConstruct
    public void getPropertiesForSale(){
        propertiesForSaleList = myPropertiesService.getPropertiesForSeller(appUserService.getLoggedSellerId());
    }

    public double getAmountSeller(){
        amountPLNSeller = financeService.getAmountByAppUserId(appUserService.getLoggedSellerId());
        return amountPLNSeller;
    }

    public double getAmountEuro(){
        BigDecimal pln = BigDecimal.valueOf(amountPLNSeller);
        BigDecimal eur = pln.divide(euro,2, RoundingMode.HALF_UP);
        return eur.doubleValue();
    }

    public double getAmountDollar(){
        BigDecimal pln = BigDecimal.valueOf(amountPLNSeller);
        BigDecimal usd = pln.divide(dollar,2, RoundingMode.HALF_UP);
        return usd.doubleValue();
    }
}

