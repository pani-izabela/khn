package application.facade;

import application.service.FlatService;
import application.service.HouseService;
import application.service.PlotService;
import application.service.UserrealassetsService;
import org.springframework.stereotype.Service;

@Service
public class PropertyFacade {
    private HouseService houseService;
    private FlatService flatService;
    private PlotService plotService;
    private UserrealassetsService userrealassetsService;

    public PropertyFacade(HouseService houseService, FlatService flatService, PlotService plotService, UserrealassetsService userrealassetsService) {
        this.houseService = houseService;
        this.flatService = flatService;
        this.plotService = plotService;
        this.userrealassetsService = userrealassetsService;
    }

    public boolean buyHouse(int appuserid, int assetsId, String assetsType){
        houseService.changeAppuser(assetsId, appuserid);
        userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
        return true;
    }

    public boolean buyFlat(int appuserid, int assetsId, String assetsType){
        flatService.changeAppuser(assetsId, appuserid);
        userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
        return true;
    }

    public boolean buyPlot(int appuserid, int assetsId, String assetsType){
        plotService.changeAppuser(assetsId, appuserid);
        userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
        return true;
    }

    public boolean buyPlotPlusHouse(int appuserid, int assetsId, int secondAssetId, String assetsType, String secondAssetType){
        if(assetsType.equals("plot")){
            plotService.changeAppuser(assetsId, appuserid);
            houseService.changeAppuser(secondAssetId, appuserid);
        }
        else if(assetsType.equals("house")){
            plotService.changeAppuser(secondAssetId, appuserid);
            houseService.changeAppuser(assetsId, appuserid);
        }
        userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
        userrealassetsService.updateUserrealassetsProperty(appuserid, secondAssetId, secondAssetType);
        return true;
    }


}
