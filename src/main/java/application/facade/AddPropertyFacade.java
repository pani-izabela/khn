package application.facade;

import application.model.Address;
import application.model.Flat;
import application.model.House;
import application.model.Plot;
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

    public Flat addFlat(Address address, Flat flat){
        Address flatAddress = addressService.addPropertyAddress(address);
        flat.setAddress(flatAddress);
        Flat addedFlat = propertyFacade.addFlat(flat);
        propertyFacade.addUserRealAssetsForFlat(addedFlat.getAppUser().getId(), addedFlat.getId());
        return addedFlat;
    }

    public House addHouse(Address address, House house){
        List<Address> houseAddressList = addressService.addHouseAddress(address);
        House addedHouse = new House();
        if(houseAddressList.size()==1){
            house.setAddress(houseAddressList.get(0));
            addedHouse = propertyFacade.addHouse(house);
            propertyFacade.addUserRealAssetsForHouse(addedHouse.getAppUser().getId(), addedHouse.getId());
        }
        else if(houseAddressList.size()==2){
            house.setAddress(houseAddressList.get(0));
            addedHouse = propertyFacade.addHouse(house);
            Address plotAddress = houseAddressList.get(1);
            Plot updatedPlot = propertyFacade.updatePlot(plotAddress, addedHouse.getId());
            propertyFacade.addUserRealAssetsForHouseAndPlot(addedHouse.getAppUser(), addedHouse, updatedPlot);
        }
        return addedHouse;
    }

    public Plot addPlot(Address address, Plot plot){
        Address plotAddress = addressService.addPropertyAddress(address);
        plot.setAddress(plotAddress);
        Plot addedPlot = propertyFacade.addPlot(plot);
        propertyFacade.addUserRealAssetsForPlot(addedPlot.getAppUser().getId(), addedPlot.getId());
        return addedPlot;
    }

    public Plot addPlot2(Address address, Plot plot){
        List<Address> plotAddressList = addressService.addHouseAddress(address);
        Plot addedPlot = new Plot();
        if(plotAddressList.size()==1){
            plot.setAddress(plotAddressList.get(0));
            addedPlot = propertyFacade.addPlot(plot);
            propertyFacade.addUserRealAssetsForPlot(addedPlot.getAppUser().getId(), addedPlot.getId());
        }
        else if(plotAddressList.size()==2){
            Address plotAddress = plotAddressList.get(0);
            plot.setAddress(plotAddress);
            Plot plotFromDb = propertyFacade.addPlot(plot);
            Address houseAddress = plotAddressList.get(1);
            House houseFromDB = propertyFacade.getHouseByAddressId(houseAddress);
            //tu coś nie zagrało, bo zapis plot się zrobił, ale nie uaktualnił się o id domu, dlaczego?
            addedPlot = propertyFacade.updatePlot(plotFromDb.getAddress(), houseFromDB.getId());
            propertyFacade.addUserRealAssetsForHouseAndPlot(addedPlot.getAppUser(), houseFromDB, addedPlot);
        }
        return addedPlot;
    }
}
