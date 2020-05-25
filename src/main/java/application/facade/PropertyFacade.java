package application.facade;

import application.model.Userrealassets;
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

    public Userrealassets buyHouse(int appuserid, int assetsId){
        houseService.changeAppuser(assetsId, appuserid);
        return userrealassetsService.addHouse(appuserid, assetsId);
    }

    public Userrealassets buyFlat(int appuserid, int assetsId){
        flatService.changeAppuser(assetsId, appuserid);
        return userrealassetsService.addFlat(appuserid, assetsId);
    }

    public Userrealassets buyPlot(int appuserid, int assetsId){
        plotService.changeAppuser(assetsId, appuserid);
        return userrealassetsService.addPlot(appuserid, assetsId);
    }

    public Userrealassets buyPlotPlusHouse(int appuserid, int assetsId, int secondAssetId, String assetsType){
        Userrealassets userrealassets = new Userrealassets();
        if(assetsType.equals("plot")){
            plotService.changeAppuser(assetsId, appuserid);
            houseService.changeAppuser(secondAssetId, appuserid);
            userrealassets = userrealassetsService.addPlot(appuserid, assetsId);
            userrealassets = userrealassetsService.updateHouse(appuserid, secondAssetId);
        }
        else if(assetsType.equals("house")){
            plotService.changeAppuser(secondAssetId, appuserid);
            houseService.changeAppuser(assetsId, appuserid);
            userrealassets = userrealassetsService.addHouse(appuserid, assetsId);
            userrealassets = userrealassetsService.updatePlot(appuserid, secondAssetId);
        }
        return userrealassets;
    }


}
