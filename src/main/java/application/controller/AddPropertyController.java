package application.controller;

import application.facade.PropertyFacade;
import application.model.*;
import application.service.AddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddPropertyController {

    private AddressService addressService;
    private PropertyFacade propertyFacade;

    public AddPropertyController(AddressService addressService, PropertyFacade propertyFacade) {
        this.addressService = addressService;
        this.propertyFacade = propertyFacade;
    }

    @GetMapping(value = "/seller/addProperty")
    public String getMyProperties() {
        return "seller/addProperty";
    }

    @ApiOperation(value = "Add new address for flat", response = Address.class)
    @PostMapping(value = "/seller/addFlatAddress")
    public @ResponseBody Address addFlatAddress(@RequestBody Address address){
        return addressService.addFlatAddress(address);
        //dostaje address bez id trzeba sprawdzić, czy nie ma już takiego adresu w bazie, jeśli nie ma
        //to dodaj tam taki adres
        //ta metoda zwraca ten zapisany w bazie aders - razem z jego id, który będzie potrzebny niżej
    }

    @ApiOperation(value = "Add new flat", response = Flat.class)
    @PostMapping(value = "/seller/addFlat")
    public @ResponseBody Flat addFlat(@RequestBody Flat flat){
        return propertyFacade.addFlat(flat);
    }

    @ApiOperation(value = "Add new address for house", response = Address.class)
    @PostMapping(value = "/seller/addHouseAddress")
    public @ResponseBody List<Address> addHouseAddress(@RequestBody Address address){
        return addressService.addHouseAddress(address);
        //dostaje adress bez id i trzeba sprawdzić, czy jest już taki adres i z jakim typem,
        //jeśli jest z typem dom - dawaj null
        //jeśli nie ma wcale to dodaj tam taki adres - to ozncza dodawanie samego domu
        //jeśli jest z typem plot to to dodaj go do bazy ale z typem dom i
        //w tabeli plot odnajduję tę dzialkę i zmieniam tam id_domu [i pobieram jego id - plotId]
        //ta metoda zwraca ten zapisany w bazie aders dla typu dom - razem z jego id, który będzie potrzebny niżej
    }

    @ApiOperation(value = "Add new house", response = House.class)
    @PostMapping(value = "/seller/addHouse")
    public @ResponseBody House addHouse(@RequestBody House house){
        return propertyFacade.addHouse(house);
        //z frontu dostaję juz gotowy obiekt house - trzeba go po prostu dodać do bazy do tabeli House
        //wpis do userrealassets i teraz mam id House, ale nie mam id Plot? skąd je wziąć?
    }

    @ApiOperation(value = "Add new plot", response = Plot.class)
    @PostMapping(value = "/seller/addPlot")
    public @ResponseBody Plot addPlot(@RequestBody Plot plot){
        return propertyFacade.addPlot(plot);
    }

    @ApiOperation(value = "Update house in plot", response = Plot.class)
    @PostMapping(value = "/seller/updatePlot")
    public @ResponseBody Plot updatePlot(@RequestBody Plot plot, House house){
        return propertyFacade.updatePlot(plot, house);
    }

    @ApiOperation(value = "Add new UserRealAssets", response = UserRealAssets.class)
    @PostMapping(value = "/seller/addProperty")
    public @ResponseBody UserRealAssets addUserRealAssets(@RequestBody @RequestParam int appUserId, @RequestParam int assetId) {
        return propertyFacade.addUserRealAssetsForFlat(appUserId, assetId);
    }

    @ApiOperation(value = "Add new UserRealAssets for house", response = UserRealAssets.class)
    @PostMapping(value = "/seller/addPropertyHouse")
    public @ResponseBody UserRealAssets addUserRealAssetsForHouse(@RequestBody @RequestParam int appUserId, @RequestParam int assetId) {
        return propertyFacade.addUserRealAssetsForHouse(appUserId, assetId);
    }

    @ApiOperation(value = "Update UserRealAssets", response = UserRealAssets.class)
    @PostMapping(value = "/seller/updateProperty")
    public @ResponseBody UserRealAssets updateUserRealAssets(@RequestBody UserRealAssets userRealAssets) {
        return null;
    }

}
