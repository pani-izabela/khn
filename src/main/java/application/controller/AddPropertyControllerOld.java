/*
package application.controller;

import application.facade.AddPropertyFacade;
import application.facade.PropertyFacade;
import application.model.*;
import application.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "KHN add property API controller")
@Controller
public class AddPropertyControllerOld {

    private AddressService addressService;
    private PropertyFacade propertyFacade;
    private AddPropertyFacade addPropertyFacade;

    public AddPropertyControllerOld(AddressService addressService, PropertyFacade propertyFacade, AddPropertyFacade addPropertyFacade) {
        this.addressService = addressService;
        this.propertyFacade = propertyFacade;
        this.addPropertyFacade = addPropertyFacade;
    }

    @GetMapping(value = "/seller/addProperty")
    public String getMyProperties() {
        return "seller/addProperty";
    }

    //----------------Nowe---------------------------------

    @ApiOperation(value = "Add new flat", response = Flat.class)
    @PostMapping(value = "/seller/addFlat")
    public @ResponseBody ResponseEntity<String> addFlat(Address address, Flat flat){
        if (addPropertyFacade.addFlat(address, flat)!=null) {
            return new ResponseEntity<>("Mieszkanie zostało dodane", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nie udało się dodać mieszkania, jest już w bazie", HttpStatus.NOT_FOUND);
        }
    }


    //-----------------Flat-------------------------------------

    @ApiOperation(value = "Add new address for flat", response = Address.class)
    @PostMapping(value = "/seller/addFlatAddress")
    public @ResponseBody Address addFlatAddress(@RequestBody Address address){
        return addressService.addFlatAddress(address);
    }

    */
/*@ApiOperation(value = "Add new flat", response = Flat.class)
    @PostMapping(value = "/seller/addFlat")
    public @ResponseBody Flat addFlat(@RequestBody Flat flat){
        return propertyFacade.addFlat(flat);
    }*//*


    //-----------------House-------------------------------------

    @ApiOperation(value = "Add new address for house", response = Address.class)
    @PostMapping(value = "/seller/addHouseAddress")
    public @ResponseBody List<Address> addHouseAddress(@RequestBody Address address){
        return addressService.addHouseAddress(address);
    }

    @ApiOperation(value = "Add new house", response = House.class)
    @PostMapping(value = "/seller/addHouse")
    public @ResponseBody House addHouse(@RequestBody House house){
        House houseFromDB = propertyFacade.addHouse(house);
        return houseFromDB;
    }

    //-----------------Plot-------------------------------------

    @ApiOperation(value = "Add new address for plot", response = Address.class)
    @PostMapping(value = "/seller/addPlotAddress")
    public @ResponseBody List<Address> addPlotAddress(@RequestBody Address address){
        return addressService.addPlotAddress(address);
    }

    @ApiOperation(value = "Add new plot", response = Plot.class)
    @PostMapping(value = "/seller/addPlot")
    public @ResponseBody Plot addPlot(@RequestBody Plot plot){
        return propertyFacade.addPlot(plot);
    }

    @ApiOperation(value = "Update house in plot", response = Plot.class)
    @PostMapping(value = "/seller/updatePlot")
    public @ResponseBody Plot updatePlot(@RequestBody @RequestParam int plotAddressId, @RequestParam int houseId){
        Address addressId = addressService.findAddressById(plotAddressId);
        return propertyFacade.updatePlot(addressId, houseId);
    }

    //-----------------UserRealAssets-------------------------------------

    @ApiOperation(value = "Add new UserRealAssets for flat", response = UserRealAssets.class)
    @PostMapping(value = "/seller/addProperty")
    public @ResponseBody UserRealAssets addUserRealAssetsForFlat(@RequestBody @RequestParam int appUserId, @RequestParam int assetId) {
        return propertyFacade.addUserRealAssetsForFlat(appUserId, assetId);
    }

    @ApiOperation(value = "Add new UserRealAssets for house", response = UserRealAssets.class)
    @PostMapping(value = "/seller/addPropertyHouse")
    public @ResponseBody UserRealAssets addUserRealAssetsForHouse(@RequestBody @RequestParam int appUserId, @RequestParam int assetId) {
        return propertyFacade.addUserRealAssetsForHouse(appUserId, assetId);
    }

    @ApiOperation(value = "Add new UserRealAssets for plot", response = UserRealAssets.class)
    @PostMapping(value = "/seller/addPropertyPlot")
    public @ResponseBody UserRealAssets addUserRealAssetsForPlot(@RequestBody @RequestParam int appUserId, @RequestParam int assetId) {
        return propertyFacade.addUserRealAssetsForPlot(appUserId, assetId);
    }

    @ApiOperation(value = "Update UserRealAssets", response = UserRealAssets.class)
    @PostMapping(value = "/seller/updateProperty")
    public @ResponseBody UserRealAssets updateUserRealAssets(@RequestBody UserRealAssets userRealAssets) {
        return null;
    }

}
*/
