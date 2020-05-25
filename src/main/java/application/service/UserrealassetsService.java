package application.service;

import application.model.Userrealassets;

import java.util.List;

public interface UserrealassetsService {
    List<Userrealassets> findAllUserrealassets();
    Userrealassets addHouse(int appuserId, int assetsId);
    Userrealassets addFlat(int appuserId, int assetsId);
    Userrealassets addPlot(int appuserId, int assetsId);
    Userrealassets updateHouse(int appuserId, int assetsId);
    Userrealassets updateFlat(int appuserId, int assetsId);
    Userrealassets updatePlot(int appuserId, int assetsId);
}
