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

    public Plot addPlot(Plot plot){
        return plotService.addPlot(plot);
    }

    public Plot updatePlot(Plot plot, House house){
        return plotService.updatePlot(plot, house);
    }

    public UserRealAssets addUserRealAssetsForPlot(int appUserId, int assetId){
        return userrealassetsService.addPlot(appUserId, assetId);
    }

    public UserRealAssets updateUserRealAssetsWithHouse(House house, Plot plot){
        return userrealassetsService.updateUserRealAssetsWithHouse(house, plot);
    }

    public House getHouseByAddressId(Address addressId){
        return houseService.findHouseByAddressId(addressId);
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

    public House addHouse(Address address, House house){
        List<Address> addressesList = addressService.findAddressByCityAndStreetAndHouseNo(address);
        House addedHouse = new House();
        if(addressesList.size()==0){
            house.setAddress(address);
            addedHouse = houseService.addHouse(house);
            userrealassetsService.addHouse(addedHouse.getAppUser().getId(), addedHouse.getId());
        }
        else if(addressesList.size()==2 || addressesList.size()==1 && addressesList.get(0).getRealAssetsId()==2){
            addedHouse = null;
        }
        else if(addressesList.size()==1 && addressesList.get(0).getRealAssetsId()==3){
            house.setAddress(address);
            addedHouse = houseService.addHouse(house);
            Plot plot = plotService.findPlotByAddressId(addressesList.get(0));
            plot.setAppUser(addedHouse.getAppUser());
            plot.setHouse(addedHouse);
            userrealassetsService.updateUserRealAssetsWithPlot(plot, addedHouse);
        }
        return addedHouse;
    }

}
