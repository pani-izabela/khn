package application.dao;

import application.model.AppUser;
import application.model.Plot;

import java.util.List;

public interface PlotDAO {

    List<Plot> findAllQuery();
    Plot findPlotByIdQuery(int id);
    Plot updateAppuser(Plot plot, AppUser appUser);
}
