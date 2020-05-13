package application.controller;

import application.dao.AppUserDAO;
import application.dao.AuctionViewDAO;
import application.dao.FinanceDAO;
import application.dao.HouseDAO;
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

    /*Tutaj warto zrobić Facade, np AuctionFacade, która będzie miała jako pola:

    - auctionViewDAO
    - financeService
    - propertyFacade

oraz kolejną Facade np PropertyFacade, która będzie miała:
houseService, plotService i flatService

    a userrealassetsService sobie zostanie w kontrolerze


    wtedy mamy zachowaną dobra praktykę i zostają 2 pola:
    - AuctionFacade
    - UserrealassetsService

czemu 3 a nie 2?:
    AuctionService -> stanie się AuctionFacade

    Mała uwaga - Facada powinna być zwykłą klasą
    ---
    */

    private HouseService houseService;
    private AuctionViewDAO auctionViewDAO;
    private FinanceDAO financeDAO;
    private UserrealassetsService userrealassetsService;
    private FlatService flatService;
    private AuctionService auctionService;

    public AuctionsController(HouseService houseService, AuctionViewDAO auctionViewDAO, FinanceDAO financeDAO, UserrealassetsService userrealassetsService, FlatService flatService, AuctionService auctionService){
        this.houseService = houseService;
        this.auctionViewDAO = auctionViewDAO;
        this.financeDAO = financeDAO;
        this.userrealassetsService = userrealassetsService;
        this.flatService = flatService;
        this.auctionService = auctionService;
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
        return auctionViewDAO.findPropertyByAssetsTypeAndAppuserRole(assetType, 2);
    }

    @ApiOperation(value = "Change appuser in house/flat/plot, change amount in finance, add row to userrealassets")
    @PostMapping(value = "/buyProperty")
    // Tak jak poradziłam Mateuszowi fajnie by to podzielić na 3 różne typy nieruchomosci,
    //czyli buyHouse, buyPlot, buyFlat - wtedy na froncie rowniez uderzamy na 3 rozne metody w kontrolerach
    public @ResponseBody ResponseEntity<String> buyProperty(int appuserid, int assetsId, String assetsType) {
        boolean result = auctionService.buyProperty(appuserid, assetsId, assetsType);
        if (result) {
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

    //----------- chwilowe pomocnicze   <--- to nie patrzę :D
    @ApiOperation(value = "Get all houses")
    @GetMapping(value = "/getAllHouses")
    public @ResponseBody List<House> getAppUsers() {
        return houseService.findAllHousesQuery();
    }

    @ApiOperation(value = "Get all from auction view")
    @GetMapping(value = "/getAll")
    public @ResponseBody List<AuctionView> getAll() {
        return auctionViewDAO.findAllQuery();
    }

    @ApiOperation(value = "Get all flats")
    @GetMapping(value = "/getFlats")
    public @ResponseBody List<Flat> getAllFlats() {
        return flatService.findAllFlatsQuery();
    }

    @GetMapping(value = "/getFinance")
    public @ResponseBody Finance getFinanceById(@RequestBody @RequestParam int financeId) {
        return financeDAO.findById(financeId);
    }

    @ApiOperation(value = "Get all userrealassets")
    @GetMapping(value = "/getUas")
    public @ResponseBody List<Userrealassets> getAllUas() {
        return userrealassetsService.findAllUserrealassets();
    }

}
