package application.controller;


import application.dao.AuctionViewDAO;
import application.dao.HouseDAO;
import application.model.AppUser;
import application.model.AuctionView;
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
    private AuctionViewDAO auctionViewDAO;

    public AuctionsController(HouseService houseService, HouseDAO houseDAO, AuctionViewDAO auctionViewDAO){
        this.houseService = houseService;
        this.houseDAO = houseDAO;
        this.auctionViewDAO = auctionViewDAO;
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

    @ApiOperation(value = "Get data for all homes from the auction")
    @GetMapping(value = "/getAllHousesView")
    public @ResponseBody List<AuctionView> getAll() {
        return auctionViewDAO.findAllQuery();
    }
}
