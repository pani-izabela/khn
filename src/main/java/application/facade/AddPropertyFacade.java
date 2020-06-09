package application.facade;

import application.model.Address;
import application.model.Flat;
import application.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddPropertyFacade {
    private AddressService addressService;
    private PropertyFacade propertyFacade;

    public AddPropertyFacade(AddressService addressService, PropertyFacade propertyFacade) {
        this.addressService = addressService;
        this.propertyFacade = propertyFacade;
    }

    public Flat addFlat(Address address, Flat flat){
        Address flatAddress = addressService.addFlatAddress(address);
        flat.setAddress(flatAddress);
        propertyFacade.addFlat(flat);
        return flat;
    }
}
