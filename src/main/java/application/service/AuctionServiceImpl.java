package application.service;

import application.model.AuctionView;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService {
    private HouseService houseService;
    private FinanceService financeService;
    private UserrealassetsService userrealassetsService;
    private FlatService flatService;
    private AuctionViewService auctionViewService;
    private PlotService plotService;

    public AuctionServiceImpl(HouseService houseService, PlotService plotService, FlatService flatService, FinanceService financeService, UserrealassetsService userrealassetsService, AuctionViewService auctionViewService) {
        this.houseService = houseService;
        this.plotService = plotService;
        this.flatService = flatService;
        this.financeService = financeService;
        this.userrealassetsService = userrealassetsService;
        this.auctionViewService = auctionViewService;
    }

    @Override
    public boolean buyProperty(int appuserid, int assetsId, String assetsType) {
        double totalCost = auctionViewService.returnTotalCost(assetsType, assetsId);
        boolean result = financeService.chcekUserAccountStatusBeforeShopping3(appuserid, totalCost);
        if(result) {
            financeService.updateAmount(appuserid);
            if(assetsType.equals("house")){
                AuctionView secondProperty = auctionViewService.returnOtherPropertyWithTheSameAdress(assetsType, assetsId);

                if(secondProperty != null){
                    plotService.changeAppuser(secondProperty.getAsset_id(), appuserid);
                    houseService.changeAppuser(assetsId, appuserid);
                    userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
                    userrealassetsService.updateUserrealassetsProperty(appuserid, secondProperty.getAsset_id(), "plot");
                }
                else{
                    houseService.changeAppuser(assetsId, appuserid);
                    userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
                }
            }
            else if(assetsType.equals("plot")){
                AuctionView secondProperty = auctionViewService.returnOtherPropertyWithTheSameAdress(assetsType, assetsId);

                if(secondProperty != null){
                    plotService.changeAppuser(assetsId, appuserid);
                    houseService.changeAppuser(secondProperty.getAsset_id(), appuserid);
                    userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
                    userrealassetsService.updateUserrealassetsProperty(appuserid, secondProperty.getAsset_id(), "house");
                }
                else{
                    plotService.changeAppuser(assetsId, appuserid);
                    userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
                }
            }
            else if(assetsType.equals("flat")){
                flatService.changeAppuser(assetsId, appuserid);
                userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
            }
            return true;
        }
        else
            return false;
    }
}
