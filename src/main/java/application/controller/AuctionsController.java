package application.controller;

import application.dao.AuctionViewDAO;
import application.dao.FinanceDAO;
import application.facade.AuctionFacade;
import application.model.*;
import application.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "KHN auctions API controller")
@Controller
public class AuctionsController {

    private AuctionFacade auctionFacade;
    private AuctionViewDAO auctionViewDAO;

    public AuctionsController(AuctionViewDAO auctionViewDAO, AuctionFacade auctionFacade) {
        this.auctionViewDAO = auctionViewDAO;
        this.auctionFacade = auctionFacade;
    }
    //----------------------------------------------------------------------------

    @GetMapping(value = "*/auctions")
    public String auctionsCustomer() {
        return "auctions";
    }

    @ApiOperation(value = "Get assets by type")
    @ApiImplicitParam(name="type", value = "RealAssets type", required = true)
    @GetMapping(value = "/getAssets")
    public @ResponseBody List<AuctionView> getAssetsByType(@RequestBody @RequestParam String assetType) {
        return auctionViewDAO.findPropertyByAssetsTypeAndAppUserRole(assetType, 2);
    }

    @ApiOperation(value = "Change appUser in flat, change amount in finance, add row to userrealassets")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "appuserid", value = "AppUser id", dataType = "int", required = true),
            @ApiImplicitParam (name = "assetsId", value = "Flat id", dataType = "int", required = true),
            @ApiImplicitParam (name = "assetsType", value = "Address realAssetsId", dataType = "String", required = true)})
    @PostMapping(value = "/buyFlat")
    public @ResponseBody ResponseEntity<String> buyFlat(int appuserid, int assetsId, String assetsType) {
        if (auctionFacade.buyFlat(appuserid, assetsId, assetsType) != null) {
            return new ResponseEntity<>("Kupiles nieruchomosc", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Brak wystarczajacych srodkow na koncie", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Change appUser in house, change amount in finance, add row to userrealassets")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "appuserid", value = "AppUser id", dataType = "int", required = true),
            @ApiImplicitParam (name = "assetsId", value = "House id", dataType = "int", required = true),
            @ApiImplicitParam (name = "assetsType", value = "Address realAssetsId", dataType = "String", required = true)})
    @PostMapping(value = "/buyHouse")
    public @ResponseBody ResponseEntity<String> buyHouse(int appuserid, int assetsId, String assetsType) {
        if (auctionFacade.buyHouse(appuserid, assetsId, assetsType) != null) {
            return new ResponseEntity<>("Kupiles nieruchomosc", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Brak wystarczajacych srodkow na koncie", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Change appUser in plot, change amount in finance, add row to userrealassets")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "appuserid", value = "AppUser id", dataType = "int", required = true),
            @ApiImplicitParam (name = "assetsId", value = "Plot id", dataType = "int", required = true),
            @ApiImplicitParam (name = "assetsType", value = "Address realAssetsId", dataType = "String", required = true)})
    @PostMapping(value = "/buyPlot")
    public @ResponseBody ResponseEntity<String> buyPlot(int appuserid, int assetsId, String assetsType) {
        if (auctionFacade.buyPlot(appuserid, assetsId, assetsType) != null) {
            return new ResponseEntity<>("Kupiles nieruchomosc", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Brak wystarczajacych srodkow na koncie", HttpStatus.NOT_FOUND);
        }
    }

}
