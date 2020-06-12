package application.service;

import application.dao.FlatDAO;
import application.dao.HouseDAO;
import application.dao.PlotDAO;
import application.dao.UserRealAssetsDAO;
import application.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserrealassetsServiceImpl implements UserrealassetsService{

    private AppUserService appUserService;
    private FlatDAO flatDAO;
    private HouseDAO houseDAO;
    private PlotDAO plotDAO;
    private UserRealAssetsDAO userrealassetsDAO;

    public UserrealassetsServiceImpl(AppUserService appUserService, FlatDAO flatDAO, HouseDAO houseDAO, PlotDAO plotDAO, UserRealAssetsDAO userrealassetsDAO) {
        this.appUserService = appUserService;
        this.flatDAO = flatDAO;
        this.houseDAO = houseDAO;
        this.plotDAO = plotDAO;
        this.userrealassetsDAO = userrealassetsDAO;
    }

    @Override
    public List<UserRealAssets> findAllUserrealassets() {
        return userrealassetsDAO.findAllUserrealassetsQuery();
    }

    @Override
    public void addUserRealAssetsForHouse(int appuserId, int assetsId) {
        UserRealAssets userrealassets = new UserRealAssets();
        userrealassets.setAppUser(appUserService.findByIdQuery(appuserId));
        userrealassets.setHouse(houseDAO.findHouseByIdQuery(assetsId));
        userrealassetsDAO.addUserrealassets(userrealassets);
    }

    @Override
    public void addUserRealAssetsForFlat(int appUserId, int assetsId) {
        UserRealAssets userrealassets = new UserRealAssets();
        userrealassets.setAppUser(appUserService.findByIdQuery(appUserId));
        userrealassets.setFlat(flatDAO.findFlatByIdQuery(assetsId));
        userrealassetsDAO.addUserrealassets(userrealassets);
    }

    @Override
    public void addUserRealAssetsForPlot(int appuserId, int assetsId) {
        UserRealAssets userrealassets = new UserRealAssets();
        userrealassets.setAppUser(appUserService.findByIdQuery(appuserId));
        userrealassets.setPlot(plotDAO.findPlotByIdQuery(assetsId));
        userrealassetsDAO.addUserrealassets(userrealassets);
    }

    @Override
    public void updateUserRealAssetsForHouse(House house) {
        //AppUser appUser = appUserService.findByIdQuery(appuserId);
        UserRealAssets userrealassets = userrealassetsDAO.findUserRealAssetsByHouseId(house);
        userrealassetsDAO.updateUserrealassetsHouse(userrealassets, house);
    }

    @Override
    public void updateUserRealAssetsForPlot(Plot plot) {
        UserRealAssets userrealassets = userrealassetsDAO.findUserRealAssetsByPlotId(plot);
        userrealassetsDAO.updateUserrealassetsPlot(userrealassets, plot);
    }

    @Override
    public void updateUserRealAssetsForFlat(Flat flat) {
        UserRealAssets userRealAssetsFromDB = userrealassetsDAO.findUserRealAssetsByFlatId(flat);
        userrealassetsDAO.updateUserrealassetsFlat(userRealAssetsFromDB, flat);
    }


    @Override
    public void updateUserRealAssetsWithHouse(House house, Plot plot) {
        UserRealAssets userRealAssetsFromDB = userrealassetsDAO.findUserRealAssetsByHouseId(house);
        userrealassetsDAO.updateUserrealassetsPlot(userRealAssetsFromDB, plot);
    }

    @Override
    public void updateUserRealAssetsWithPlot(Plot plot, House house) {
        UserRealAssets userRealAssetsFromDB = userrealassetsDAO.findUserRealAssetsByPlotId(plot);
        userrealassetsDAO.updateUserrealassetsHouse(userRealAssetsFromDB, house);
    }

}
