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

    private HouseService houseService;
    private HouseDAO houseDAO;
    private AuctionViewDAO auctionViewDAO;
    private FinanceDAO financeDAO;
    private AppUserDAO appUserDAO;
    private FinanceService financeService;
    private UserrealassetsService userrealassetsService;
    private FlatService flatService;
    private AuctionViewService auctionViewService;
    private PlotService plotService;

    public AuctionsController(HouseService houseService, HouseDAO houseDAO, AuctionViewDAO auctionViewDAO, FinanceDAO financeDAO, AppUserDAO appUserDAO, FinanceService financeService, UserrealassetsService userrealassetsService, FlatService flatService, AuctionViewService auctionViewService, PlotService plotService){
        this.houseService = houseService;
        this.houseDAO = houseDAO;
        this.auctionViewDAO = auctionViewDAO;
        this.financeDAO = financeDAO;
        this.appUserDAO = appUserDAO;
        this.financeService = financeService;
        this.userrealassetsService = userrealassetsService;
        this.flatService = flatService;
        this.auctionViewService = auctionViewService;
        this.plotService = plotService;
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
        double totalCost = auctionViewService.returnTotalCost(assetsType, assetsId);
        boolean result = financeService.chcekUserAccountStatusBeforeShopping3(appuserid, totalCost);
        if(result) {
            financeService.updateAmount(appuserid);
            if(assetsType.equals("house")){
                AuctionView secondProperty = auctionViewService.returnOtherPropertyWithTheSameAdress(assetsType, assetsId);

                if(secondProperty != null){
                    houseService.changeAppuser(assetsId, appuserid);
                    plotService.changeAppuser(secondProperty.getAsset_id(), appuserid);
                    userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
                    userrealassetsService.updateUserrealassetsProperty(appuserid, secondProperty.getAsset_id(), "plot");
                }
                else{
                    houseService.changeAppuser(assetsId, appuserid);
                    userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
                }
            }
            else if(assetsType.equals("plot")){
                AuctionView secondProperty = auctionViewService.returnOtherPropertyWithTheSameAdress(assetsType, assetsId);

                if(secondProperty != null){
                    plotService.changeAppuser(assetsId, appuserid);
                    houseService.changeAppuser(secondProperty.getAsset_id(), appuserid);
                    userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
                    userrealassetsService.updateUserrealassetsProperty(appuserid, secondProperty.getAsset_id(), "house");
                }
                else{
                    plotService.changeAppuser(assetsId, appuserid);
                    userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
                }
            }
            else if(assetsType.equals("flat")){
                flatService.changeAppuser(assetsId, appuserid);
                userrealassetsService.addUserrealassets(appuserid, assetsId, assetsType);
            }
            return new ResponseEntity<>("Kupiłeś nieruchomość", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Brak wystarczających środków na koncie", HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete auctionView by id")
    @ApiImplicitParam(name="id", value = "AuctionView id", required = true)
    @DeleteMapping(value = "/deleteAuctionView")
    public @ResponseBody void deleteAustionView(@RequestParam int auctionViewId){
        auctionViewDAO.deleteById(auctionViewId);
    }


}
