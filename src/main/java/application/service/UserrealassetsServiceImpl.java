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
    public UserRealAssets addHouse(int appuserId, int assetsId) {
        UserRealAssets userrealassets = new UserRealAssets();
        userrealassets.setAppUser(appUserService.findByIdQuery(appuserId));
        userrealassets.setHouse(houseDAO.findHouseByIdQuery(assetsId));
        return userrealassetsDAO.addUserrealassets(userrealassets);
    }

    @Override
    public UserRealAssets addFlat(int appuserId, int assetsId) {
        UserRealAssets userrealassets = new UserRealAssets();
        userrealassets.setAppUser(appUserService.findByIdQuery(appuserId));
        userrealassets.setFlat(flatDAO.findFlatByIdQuery(assetsId));
        return userrealassetsDAO.addUserrealassets(userrealassets);
    }

    @Override
    public UserRealAssets addPlot(int appuserId, int assetsId) {
        UserRealAssets userrealassets = new UserRealAssets();
        userrealassets.setAppUser(appUserService.findByIdQuery(appuserId));
        userrealassets.setPlot(plotDAO.findPlotByIdQuery(assetsId));
        return userrealassetsDAO.addUserrealassets(userrealassets);
    }

    @Override
    public UserRealAssets updateHouse(int appuserId, int assetsId) {
        AppUser appUser = appUserService.findByIdQuery(appuserId);
        UserRealAssets userrealassets = userrealassetsDAO.findUserrealAssetsByUserId(appUser);
        House house = houseDAO.findHouseByIdQuery(assetsId);
        return userrealassetsDAO.updateUserrealassetsHouse(userrealassets, house);
    }

    @Override
    public UserRealAssets updateFlat(int appuserId, int assetsId) {
        AppUser appUser = appUserService.findByIdQuery(appuserId);
        UserRealAssets userrealassets = userrealassetsDAO.findUserrealAssetsByUserId(appUser);
        Flat flat = flatDAO.findFlatByIdQuery(assetsId);
        return userrealassetsDAO.updateUserrealassetsFlat(userrealassets, flat);
    }

    @Override
    public UserRealAssets updatePlot(int appuserId, int assetsId) {
        AppUser appUser = appUserService.findByIdQuery(appuserId);
        UserRealAssets userrealassets = userrealassetsDAO.findUserrealAssetsByUserId(appUser);
        Plot plot = plotDAO.findPlotByIdQuery(assetsId);
        return userrealassetsDAO.updateUserrealassetsPlot(userrealassets, plot);
    }

}
