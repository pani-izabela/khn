package application.controller;

import application.dao.AppUserDAO;
import application.dao.AuctionViewDAO;
import application.dao.FinanceDAO;
import application.dao.HouseDAO;
import application.model.AppUser;
import application.model.AuctionView;
import application.model.Finance;
import application.model.House;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ApiOperation(value = "Get assets by type")
    @ApiImplicitParam(name="type", value = "RealAssets type", required = true)
    @GetMapping(value = "/getAssets")
    public @ResponseBody List<AuctionView> getAssetsByType(@RequestBody @RequestParam String assetType) {
        return auctionViewDAO.findAllQueryByAssetsType(assetType);
    }

    /*@ApiOperation(value = "Get finance by appuserid")
    @ApiImplicitParam(name="apppuserid", required = true)
    @GetMapping(value = "/getFinance")
    public @ResponseBody Finance getFinanceByAppuserid(@RequestBody @RequestParam int appuserid) {
        return financeService.chcekUserAccountStatusBeforeShopping(appuserid);
        //return financeDAO.findByAppuseridQuery(appUserDAO.findByIdQuery(appuserid));
    }*/

    @ApiOperation(value = "Get finance by appuserid")
    @ApiImplicitParam(name="apppuserid", required = true)
    @GetMapping(value = "/buyProperty")
    public @ResponseBody ResponseEntity<String> getFinanceByAppuserid(@RequestBody @RequestParam int appuserid, @RequestParam int assetsId, @RequestParam String assetsType) {
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
            return new ResponseEntity<>("Brak wystarczających środkó na koncie", HttpStatus.NOT_FOUND);
    }

}
