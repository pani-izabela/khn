package application.controller;


import application.dao.HouseDAO;
import application.model.AppUser;
import application.model.House;
import application.service.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value = "KHN auctions API controller")
@Controller
public class AuctionsController {

    private HouseService houseService;
    private HouseDAO houseDAO;

    public AuctionsController(HouseService houseService, HouseDAO houseDAO){
        this.houseService = houseService;
        this.houseDAO = houseDAO;
    }

    //----------------------------------------------------------------------------

    @GetMapping(value = "*/auctions")
    public String auctionsCustomer() {
        return "auctions";
    }

    @ApiOperation(value = "Get all houses")
    @GetMapping(value = "/getAllHouses")
    public @ResponseBody List<House> getAppUsers() {
        return houseService.findAllHousesQuery();
    }
}
