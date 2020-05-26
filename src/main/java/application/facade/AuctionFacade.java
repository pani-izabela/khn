package application.facade;

import application.model.AuctionView;
import application.model.UserRealAssets;
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

    public UserRealAssets buyHouse(int appuserid, int assetsId, String assetsType){
        financialOperations(appuserid, assetsId, assetsType);
        AuctionView secondProperty = auctionViewService.getPropertyWithTheSameAddress(assetsType, assetsId);
        if(secondProperty != null){
            return propertyFacade.buyPlotPlusHouse(appuserid, assetsId, secondProperty.getAsset_id(), assetsType);
        }
        else {
            return propertyFacade.buyHouse(appuserid, assetsId);
        }
    }

    public UserRealAssets buyFlat(int appuserid, int assetsId, String assetsType){
        financialOperations(appuserid, assetsId, assetsType);
        return propertyFacade.buyFlat(appuserid, assetsId);
    }

    public UserRealAssets buyPlot(int appuserid, int assetsId, String assetsType){
        financialOperations(appuserid, assetsId, assetsType);
        AuctionView secondProperty = auctionViewService.getPropertyWithTheSameAddress(assetsType, assetsId);
        if(secondProperty != null){
            return propertyFacade.buyPlotPlusHouse(appuserid, assetsId, secondProperty.getAsset_id(), assetsType);
        }
        else {
            return propertyFacade.buyPlot(appuserid, assetsId);
        }
    }

    //-------operacje finansowe
    private void financialOperations(int appuserid, int assetsId, String assetsType){
        double totalCost = auctionViewService.returnTotalCost(assetsType, assetsId);
        boolean result = financeService.chcekUserAccountStatus(appuserid, totalCost);
        if(result){
            financeService.updateAmount(appuserid);
        }
    }




}
