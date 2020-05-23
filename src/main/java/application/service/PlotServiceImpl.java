package application.service;

import application.dao.HouseDAO;
import application.dao.PlotDAO;
import application.model.AppUser;
import application.model.House;
import application.model.Plot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotServiceImpl implements PlotService {

    private PlotDAO plotDAO;
    private AppUserServiceImpl appUserService;

    public PlotServiceImpl(PlotDAO plotDAO, AppUserServiceImpl appUserService) {
        this.plotDAO = plotDAO;
        this.appUserService = appUserService;
    }

    @Override
    public List<Plot> findAllPlotsQuery() {
        return plotDAO.findAllQuery();
    }

    @Override
    public Plot changeAppuser(int plotId, int appUserId) {
        AppUser appUser = appUserService.findByIdQuery(appUserId);
        Plot plot = plotDAO.findPlotByIdQuery(plotId);
        return plotDAO.updateAppuser(plot, appUser);
    }
}