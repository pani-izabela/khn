package application.controller;

import application.facade.AddPropertyFacade;
import application.facade.PropertyFacade;
import application.model.*;
import application.service.AddressService;
import application.wrapper.FlatWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "KHN add property API controller")
@Controller
public class AddPropertyController {

    private AddressService addressService;
    private PropertyFacade propertyFacade;
    private AddPropertyFacade addPropertyFacade;

    public AddPropertyController(AddressService addressService, PropertyFacade propertyFacade, AddPropertyFacade addPropertyFacade) {
        this.addressService = addressService;
        this.propertyFacade = propertyFacade;
        this.addPropertyFacade = addPropertyFacade;
    }

    @GetMapping(value = "/seller/addProperty")
    public String getMyProperties() {
        return "seller/addProperty";
    }

    //----------------Nowe---------------------------------

    @ApiOperation(value = "Add new flat")
    @PostMapping(value = "/seller/addFlat")
    public @ResponseBody ResponseEntity<String> addFlat(@RequestBody FlatWrapper flatWrapper) {
        Address address = flatWrapper.getAddress();
        Flat flat = flatWrapper.getFlat();
        if (addPropertyFacade.addFlat(address, flat) != null) {
            return new ResponseEntity<>("Mieszkanie zostało dodane", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nie udało się dodać mieszkania, jest już w bazie", HttpStatus.NOT_FOUND);
        }
    }
}