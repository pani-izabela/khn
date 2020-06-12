package application.facade;

import application.model.*;
import application.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddPropertyFacade {
    private AddressService addressService;
    private PropertyFacade propertyFacade;

    public AddPropertyFacade(AddressService addressService, PropertyFacade propertyFacade) {
        this.addressService = addressService;
        this.propertyFacade = propertyFacade;
    }


    public House addHouse(Address address, House house){
        List<Address> addressesList = addressService.findAddressByCityAndStreetAndHouseNo(address);
        House addedHouse = new House();
        if(addressesList.size()==0){
            house.setAddress(address);
            addedHouse = propertyFacade.addHouse(house);
            propertyFacade.addUserRealAssetsForHouse(addedHouse.getAppUser().getId(), addedHouse.getId());
        }
        else if(addressesList.size()==2 || addressesList.size()==1 && addressesList.get(0).getRealAssetsId()==2){
            addedHouse = null;
        }
        else if(addressesList.size()==1 && addressesList.get(0).getRealAssetsId()==3){
            house.setAddress(address);
            addedHouse = propertyFacade.addHouse(house);
            Plot plot = propertyFacade.getPlotByAddressId(addressesList.get(0));
            plot.setAppUser(addedHouse.getAppUser());
            plot.setHouse(addedHouse);
            propertyFacade.updateUserRealAssetsWithPlot(plot, addedHouse);
        }
        return addedHouse;
    }

    public Plot addPlot(Address address, Plot plot){
        List<Address> addressesList = addressService.findAddressByCityAndStreetAndHouseNo(address);
        Plot addedPlot = new Plot();
        if(addressesList.size()==0){
            plot.setAddress(address);
            addedPlot = propertyFacade.addPlot(plot);
            propertyFacade.addUserRealAssetsForPlot(addedPlot.getAppUser().getId(), addedPlot.getId());
        }
        else if(addressesList.size()==2 || addressesList.size()==1 && addressesList.get(0).getRealAssetsId()==3){
            addedPlot = null;
        }
        else if(addressesList.size()==1 && addressesList.get(0).getRealAssetsId()==2){
            plot.setAddress(address);
            addedPlot = propertyFacade.addPlot(plot);
            House house = propertyFacade.getHouseByAddressId(addressesList.get(0));
            house.setAppUser(addedPlot.getAppUser());
            addedPlot = propertyFacade.updatePlot(addedPlot, house);
            propertyFacade.updateUserRealAssetsWithHouse(house, addedPlot);
        }
        return addedPlot;
    }
}
