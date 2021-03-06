package application.service;

import application.model.Address;
import application.model.Flat;
import application.model.House;
import application.model.Plot;

import java.util.List;

public interface PlotService {
    List<Plot> findAllPlotsQuery();
    Plot changeAppuser(int plotId, int appUserId);
    Plot addPlot(Plot plot);
    Plot updatePlot(Plot plot, House house);
    Plot findPlotByAddressId(Address addressId);
    Plot findPlotById(int id);
}
