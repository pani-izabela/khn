package application.service;

import application.model.*;

import java.util.List;

public interface UserrealassetsService {
    List<UserRealAssets> findAllUserrealassets();
    void addUserRealAssetsForHouse(int appuserId, int assetsId);
    void addUserRealAssetsForFlat(int appUserId, int assetsId);
    void addUserRealAssetsForPlot(int appuserId, int assetsId);

    void updateUserRealAssetsForHouse(House house);
    void updateUserRealAssetsForPlot(Plot plot);
    void updateUserRealAssetsForFlat(Flat flat);

    void updateUserRealAssetsWithHouse(House house, Plot plot);
    void updateUserRealAssetsWithPlot(Plot plot, House house);
}
