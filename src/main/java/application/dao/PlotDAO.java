package application.dao;

import application.model.Address;
import application.model.AppUser;
import application.model.House;
import application.model.Plot;

import java.util.List;

public interface PlotDAO {

    List<Plot> findAllQuery();
    Plot findPlotByIdQuery(int id);
    Plot updateAppuser(Plot plot, AppUser appUser);
    Plot addPlot(Plot plot);
    Plot findPlotByAddressId(Address addressId);
    Plot updateHouseId(Plot plot, House house);
}
