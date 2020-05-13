package application.service;

import application.dao.FlatDAO;
import application.dao.HouseDAO;
import application.dao.PlotDAO;
import application.dao.UserrealassetsDAO;
import application.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserrealassetsServiceImpl implements UserrealassetsService{

    private AppUserService appUserService;
    private FlatDAO flatDAO;
    private HouseDAO houseDAO;
    private PlotDAO plotDAO;
    private UserrealassetsDAO userrealassetsDAO;

    public UserrealassetsServiceImpl(AppUserService appUserService, FlatDAO flatDAO, HouseDAO houseDAO, PlotDAO plotDAO, UserrealassetsDAO userrealassetsDAO) {
        this.appUserService = appUserService;
        this.flatDAO = flatDAO;
        this.houseDAO = houseDAO;
        this.plotDAO = plotDAO;
        this.userrealassetsDAO = userrealassetsDAO;
    }

    @Override
    public List<Userrealassets> findAllUserrealassets() {
        return userrealassetsDAO.findAllUserrealassetsQuery();
    }

    @Override
    //Zamiast jednej wspólnej metody to podzieliłabym na 3 - dla house, flat i plot oddzielnie - Wtedy nie będziemy miec też tych ifów
    public Userrealassets addUserrealassets(int appuserId, int assetsId, String assetsType) {
        Userrealassets userrealassets = new Userrealassets();
        AppUser appUser = appUserService.findByIdQuery(appuserId);
        userrealassets.setAppUser(appUser);
        if(assetsType.equals("flat")){
            Flat flat = flatDAO.findFlatByIdQuery(assetsId);
            userrealassets.setFlat(flat);
        }
        else if(assetsType.equals("house")){
            House house = houseDAO.findHouseByIdQuery(assetsId);
            userrealassets.setHouse(house);
        }
        else if(assetsType.equals("plot")){
            Plot plot = plotDAO.findPlotByIdQuery(assetsId);
            userrealassets.setPlot(plot);
        }
        return userrealassetsDAO.addUserrealassets(userrealassets);
    }

    @Override
    //Tak samo jak dla addUserrealassets
    public Userrealassets updateUserrealassetsProperty(int appuserId, int assetsId, String assetsType) {
        AppUser appUser = appUserService.findByIdQuery(appuserId);
        Userrealassets userrealassets = userrealassetsDAO.findUserrealAssetsByUserId(appUser);
        if(assetsType.equals("house")){
            House house = houseDAO.findHouseByIdQuery(assetsId);
            userrealassets = userrealassetsDAO.updateUserrealassetsHouse(userrealassets, house);
        }
        else if(assetsType.equals("plot")){
            Plot plot = plotDAO.findPlotByIdQuery(assetsId);
            userrealassets = userrealassetsDAO.updateUserrealassetsPlot(userrealassets, plot);
        }
        else if(assetsType.equals("flat")){
            Flat flat = flatDAO.findFlatByIdQuery(assetsId);
            userrealassets = userrealassetsDAO.updateUserrealassetsFlat(userrealassets, flat);
        }
        return userrealassets;
    }

}
