package application.controller;

import application.facade.AddPropertyFacade;
import application.facade.PropertyFacade;
import application.model.*;
import application.service.AddressService;
import application.wrapper.FlatWrapper;
import application.wrapper.HouseWrapper;
import application.wrapper.PlotWrapper;
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

    private AddPropertyFacade addPropertyFacade;
    private PropertyFacade propertyFacade;

    public AddPropertyController(AddPropertyFacade addPropertyFacade, PropertyFacade propertyFacade) {
        this.addPropertyFacade = addPropertyFacade;
        this.propertyFacade = propertyFacade;
    }

    @GetMapping(value = "/seller/addProperty")
    public String getMyProperties() {
        return "seller/addProperty";
    }

    //----------------Nowe---------------------------------

    @ApiOperation(value = "Add new flat")
    @PostMapping(value = "/seller/addFlat")
    public @ResponseBody ResponseEntity<Flat> addFlat(@RequestBody FlatWrapper flatWrapper) {
        Flat flat = propertyFacade.addFlat(flatWrapper.getAddress(), flatWrapper.getFlat());
        if (flat != null) {
            return new ResponseEntity<Flat>(flat, HttpStatus.OK);
        } else {
            return new ResponseEntity<Flat>(flat, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Add new house")
    @PostMapping(value = "/seller/addHouse")
    public @ResponseBody ResponseEntity<House> addHouse(@RequestBody HouseWrapper houseWrapper) {
        House house = propertyFacade.addHouse(houseWrapper.getAddress(), houseWrapper.getHouse());
        if (house != null) {
            return new ResponseEntity<House>(house, HttpStatus.OK);
        } else {
            return new ResponseEntity<House>(house, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Add new plot")
    @PostMapping(value = "/seller/addPlot")
    public @ResponseBody ResponseEntity<Plot> addPlot(@RequestBody PlotWrapper plotWrapper) {
        Plot plot = addPropertyFacade.addPlot(plotWrapper.getAddress(), plotWrapper.getPlot());
        if (plot != null) {
            return new ResponseEntity<Plot>(plot, HttpStatus.OK);
        } else {
            return new ResponseEntity<Plot>(plot, HttpStatus.NOT_FOUND);
        }
    }
}