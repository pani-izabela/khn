package application.facade;

import application.model.*;
import application.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyFacade {
    private HouseService houseService;
    private FlatService flatService;
    private PlotService plotService;
    private UserrealassetsService userrealassetsService;
    private AddressService addressService;

    public PropertyFacade(HouseService houseService, FlatService flatService, PlotService plotService, UserrealassetsService userrealassetsService, AddressService addressService) {
        this.houseService = houseService;
        this.flatService = flatService;
        this.plotService = plotService;
        this.userrealassetsService = userrealassetsService;
        this.addressService = addressService;
    }

    public UserRealAssets buyHouse(int appuserid, int assetsId){
        houseService.changeAppuser(assetsId, appuserid);
        return userrealassetsService.addHouse(appuserid, assetsId);
    }

    public UserRealAssets buyFlat(int appuserid, int assetsId){
        flatService.changeAppuser(assetsId, appuserid);
        return userrealassetsService.addFlat(appuserid, assetsId);
    }

    public UserRealAssets buyPlot(int appuserid, int assetsId){
        plotService.changeAppuser(assetsId, appuserid);
        return userrealassetsService.addPlot(appuserid, assetsId);
    }

    public UserRealAssets buyPlotPlusHouse(int appuserid, int assetsId, int secondAssetId, String assetsType){
        UserRealAssets userrealassets = new UserRealAssets();
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


    //--------------metod do dodawania nieruchomości

    public Flat addFlat(Flat flat){
        return flatService.addFlat(flat);
    }

    public House addHouse(House house){
        return houseService.addHouse(house);
    }

    public Plot addPlot(Plot plot){
        return plotService.addPlot(plot);
    }

    public Plot updatePlot(Address plotAddressId, int houseId){
        Plot plot = plotService.findPlotByAddressId(plotAddressId);
        House house = houseService.findHouseById(houseId);
        return plotService.updatePlot(plot, house);
    }

    public Plot updatePlot(Plot plot, House house){
        return plotService.updatePlot(plot, house);
    }

    public UserRealAssets addUserRealAssetsForFlat(int appUserId, int assetId){
        return userrealassetsService.addFlat(appUserId, assetId);
    }

    public UserRealAssets addUserRealAssetsForHouse(int appUserId, int assetId){
        return userrealassetsService.addHouse(appUserId, assetId);
    }

    public UserRealAssets addUserRealAssetsForPlot(int appUserId, int assetId){
        return userrealassetsService.addPlot(appUserId, assetId);
    }

    public UserRealAssets addUserRealAssetsForHouseAndPlot(AppUser appUserId, House house, Plot plot){
        return userrealassetsService.addHouseAndPlot(appUserId, house, plot);
    }
    public UserRealAssets updateUserRealAssetsWithHouse(House house, Plot plot){
        return userrealassetsService.updateUserRealAssetsWithHouse(house, plot);
    }

    public UserRealAssets updateUserRealAssetsWithPlot(Plot plot, House house){
        return userrealassetsService.updateUserRealAssetsWithPlot(plot, house);
    }

    public House getHouseByAddressId(Address addressId){
        return houseService.findHouseByAddressId(addressId);
    }

    public Plot getPlotByAddressId(Address addressId){
        return plotService.findPlotByAddressId(addressId);
    }

    //-----------------z AddProperty
    public Flat addFlat(Address address, Flat flat){
        List<Address> addressesList = addressService.findAddressByCityAndStreetAndHouseNo(address);
        if(addressesList.isEmpty()){
            flat.setAddress(address);
            Flat addedFlat = flatService.addFlat(flat);
            userrealassetsService.addFlat(addedFlat.getAppUser().getId(), addedFlat.getId());
            return addedFlat;
        }
        else{
            return null;
        }
    }

}
