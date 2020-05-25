package application.facade;

import application.model.*;
import application.service.AuctionViewService;
import application.service.FinanceService;
import application.service.UserrealassetsService;
import org.springframework.stereotype.Service;

@Service
public class AuctionFacade {

    private UserrealassetsService userrealassetsService;
    private AuctionViewService auctionViewService;
    private FinanceService financeService;
    private PropertyFacade propertyFacade;

    public AuctionFacade(UserrealassetsService userrealassetsService, AuctionViewService auctionViewService, FinanceService financeService, PropertyFacade propertyFacade) {
        this.userrealassetsService = userrealassetsService;
        this.auctionViewService = auctionViewService;
        this.financeService = financeService;
        this.propertyFacade = propertyFacade;
    }

    //--------operacje kupowania

    public boolean buyHouse(int appuserid, int assetsId, String assetsType){
        boolean result = false;
        financialOperations(appuserid, assetsId, assetsType);
        AuctionView secondProperty = auctionViewService.getPropertyWithTheSameAddress(assetsType, assetsId);
        if(secondProperty != null){
            result = propertyFacade.buyPlotPlusHouse(appuserid, assetsId, secondProperty.getAsset_id(), assetsType, "plot");
        }
        else {
            result = propertyFacade.buyHouse(appuserid, assetsId, assetsType);
        }
        return result;
    }

    public boolean buyFlat(int appuserid, int assetsId, String assetsType){
        financialOperations(appuserid, assetsId, assetsType);
        return propertyFacade.buyFlat(appuserid, assetsId, assetsType);
    }

    public boolean buyPlot(int appuserid, int assetsId, String assetsType){
        boolean result = false;
        financialOperations(appuserid, assetsId, assetsType);
        AuctionView secondProperty = auctionViewService.getPropertyWithTheSameAddress(assetsType, assetsId);
        if(secondProperty != null){
            result = propertyFacade.buyPlotPlusHouse(appuserid, assetsId, secondProperty.getAsset_id(), assetsType, "house");
        }
        else {
            result = propertyFacade.buyPlot(appuserid, assetsId, assetsType);
        }
        return result;
    }

    //-------operacje finansowe
    private void financialOperations(int appuserid, int assetsId, String assetsType){
        double totalCost = auctionViewService.returnTotalCost(assetsType, assetsId);
        boolean result = financeService.chcekUserAccountStatus(appuserid, totalCost);
        if(result){
            financeService.updateAmount(appuserid);
        }
    }

    //----------operacje przepinania nieruchmości



}
