package application.dao;

import application.model.*;

import java.util.List;

public interface UserRealAssetsDAO {

    List<UserRealAssets> findAllUserrealassetsQuery();
    UserRealAssets findUserRealAssetsById(int id);
    UserRealAssets findUserRealAssetsByHouseId(House house);
    UserRealAssets findUserRealAssetsByPlotId(Plot plot);
    UserRealAssets findUserRealAssetsByFlatId(Flat flat);
    UserRealAssets addUserrealassets(UserRealAssets userRealAssets);
    UserRealAssets updateUserrealassetsHouse(UserRealAssets userRealAssets, House house);
    UserRealAssets updateUserrealassetsPlot(UserRealAssets userRealAssets, Plot plot);
    UserRealAssets updateUserrealassetsFlat(UserRealAssets userRealAssets, Flat flat);
}
