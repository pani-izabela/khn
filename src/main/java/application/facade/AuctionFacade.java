package application.facade;

import application.model.*;
import application.service.AuctionViewService;
import application.service.FinanceService;
import org.springframework.stereotype.Service;

@Service
public class AuctionFacade {

    private AuctionViewService auctionViewService;
    private FinanceService financeService;
    private PropertyFacade propertyFacade;

    public AuctionFacade(AuctionViewService auctionViewService, FinanceService financeService, PropertyFacade propertyFacade) {
        this.auctionViewService = auctionViewService;
        this.financeService = financeService;
        this.propertyFacade = propertyFacade;
    }

    //--------operacje kupowania

    public House buyHouse(int appUserId, int assetsId, String assetsType){
        financialOperations(appUserId, assetsId, assetsType);
        AuctionView secondProperty = auctionViewService.getPropertyWithTheSameAddress(assetsType, assetsId);
        if(secondProperty != null){
            return propertyFacade.buyHousePlusPlot(appUserId, assetsId, secondProperty.getAsset_id());
        }
        else {
            return propertyFacade.buyHouse(appUserId, assetsId);
        }
    }

    public Flat buyFlat(int appUserId, int assetsId, String assetsType){
        financialOperations(appUserId, assetsId, assetsType);
        return propertyFacade.buyFlat(appUserId, assetsId);
    }

    public Plot buyPlot(int appUserId, int assetsId, String assetsType){
        financialOperations(appUserId, assetsId, assetsType);
        AuctionView secondProperty = auctionViewService.getPropertyWithTheSameAddress(assetsType, assetsId);
        if(secondProperty != null){
            return propertyFacade.buyPlotPlusHouse(appUserId, assetsId, secondProperty.getAsset_id());
        }
        else {
            return propertyFacade.buyPlot(appUserId, assetsId);
        }
    }

    //-------operacje finansowe
    private void financialOperations(int appUserId, int assetsId, String assetsType){
        double totalCost = auctionViewService.returnTotalCost(assetsType, assetsId);
        boolean result = financeService.chcekUserAccountStatus(appUserId, totalCost);
        if(result){
            financeService.updateAmount(appUserId);
        }
    }




}
