package application.controller;

import application.dao.AuctionViewDAO;
import application.dao.FinanceDAO;
import application.facade.AuctionFacade;
import application.model.*;
import application.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

    @ApiOperation(value = "Change appuser in flat, change amount in finance, add row to userrealassets")
    @PostMapping(value = "/buyFlat")
    public @ResponseBody ResponseEntity<String> buyProperty(int appuserid, int assetsId, String assetsType) {
        if (auctionFacade.buyFlat(appuserid, assetsId, assetsType) != null) {
            return new ResponseEntity<>("Kupiles nieruchomosc", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Brak wystarczajacych srodkow na koncie", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Change appuser in house, change amount in finance, add row to userrealassets")
    @PostMapping(value = "/buyHouse")
    public @ResponseBody ResponseEntity<String> buyHouse(int appuserid, int assetsId, String assetsType) {
        if (auctionFacade.buyHouse(appuserid, assetsId, assetsType) != null) {
            return new ResponseEntity<>("Kupiles nieruchomosc", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Brak wystarczajacych srodkow na koncie", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Change appuser in plot, change amount in finance, add row to userrealassets")
    @PostMapping(value = "/buyPlot")
    public @ResponseBody ResponseEntity<String> buyPlot(int appuserid, int assetsId, String assetsType) {
        if (auctionFacade.buyPlot(appuserid, assetsId, assetsType) != null) {
            return new ResponseEntity<>("Kupiles nieruchomosc", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Brak wystarczajacych srodkow na koncie", HttpStatus.NOT_FOUND);
        }
    }

    /*@ApiOperation(value = "Delete auctionView by id")
    @ApiImplicitParam(name="id", value = "AuctionView id", required = true)
    @DeleteMapping(value = "/deleteAuctionView")
    public @ResponseBody void deleteAustionView(@RequestParam int auctionViewId){
        auctionViewDAO.deleteById(auctionViewId);
    }*/


}
