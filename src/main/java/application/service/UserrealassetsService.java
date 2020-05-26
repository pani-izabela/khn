package application.service;

import application.model.UserRealAssets;

import java.util.List;

public interface UserrealassetsService {
    List<UserRealAssets> findAllUserrealassets();
    UserRealAssets addHouse(int appuserId, int assetsId);
    UserRealAssets addFlat(int appuserId, int assetsId);
    UserRealAssets addPlot(int appuserId, int assetsId);
    UserRealAssets updateHouse(int appuserId, int assetsId);
    UserRealAssets updateFlat(int appuserId, int assetsId);
    UserRealAssets updatePlot(int appuserId, int assetsId);
}
