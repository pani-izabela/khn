package application.controller;

import application.dao.AppUserDAO;
import application.dao.AuctionViewDAO;
import application.dao.FinanceDAO;
import application.dao.HouseDAO;
import application.model.*;
import application.service.FinanceService;
import application.service.FlatService;
import application.service.HouseService;
import application.service.UserrealassetsService;
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

    private HouseService houseService;
    private HouseDAO houseDAO;
    private AuctionViewDAO auctionViewDAO;
    private FinanceDAO financeDAO;
    private AppUserDAO appUserDAO;
    private FinanceService financeService;
    private UserrealassetsService userrealassetsService;
    private FlatService flatService;

    public AuctionsController(HouseService houseService, HouseDAO houseDAO, AuctionViewDAO auctionViewDAO, FinanceDAO financeDAO, AppUserDAO appUserDAO, FinanceService financeService, UserrealassetsService userrealassetsService, FlatService flatService){
        this.houseService = houseService;
        this.houseDAO = houseDAO;
        this.auctionViewDAO = auctionViewDAO;
        this.financeDAO = financeDAO;
        this.appUserDAO = appUserDAO;
        this.financeService = financeService;
        this.userrealassetsService = userrealassetsService;
        this.flatService = flatService;
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

    @ApiOperation(value = "Get assets by type")
    @ApiImplicitParam(name="type", value = "RealAssets type", required = true)
    @GetMapping(value = "/getAssets")
    public @ResponseBody List<AuctionView> getAssetsByType(@RequestBody @RequestParam String assetType) {
        //return auctionViewDAO.findAllQueryByAssetsType(assetType);
        return auctionViewDAO.findPropertyByAssetsTypeAndAppuserRole(assetType, 2);
    }

    @ApiOperation(value = "Change appuser in house/flat/plot, change amount in finance, add row to userrealassets")
    @PostMapping(value = "/buyProperty")
    public @ResponseBody ResponseEntity<String> buyProperty(int appuserid, int assetsId, String assetsType) {
        boolean result = financeService.chcekUserAccountStatusBeforeShopping(appuserid, assetsId, assetsType);
        if(result) {
            financeService.updateAmount(appuserid);
            if(assetsType.equals("house")){
                houseService.changeAppuser(assetsId, appuserid);
            }
            else if(assetsType.equals("flat")){
                flatService.changeAppuser(assetsId, appuserid);
            }
            userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
            //jeśli tio wyżej da true to wołaj kolejną metodę z serwisu do zakupu
            return new ResponseEntity<>("Kupiłeś nieruchomość", HttpStatus.OK);
        }
        else
            //jeśli to wyżej zwróci false to wyświetl komunikat, że nie ma środkó na zakup
            return new ResponseEntity<>("Brak wystarczających środków na koncie", HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete auctionView by id")
    @ApiImplicitParam(name="id", value = "AuctionView id", required = true)
    @DeleteMapping(value = "/deleteAuctionView")
    public @ResponseBody void deleteAustionView(@RequestParam int auctionViewId){
        auctionViewDAO.deleteById(auctionViewId);
    }


}
