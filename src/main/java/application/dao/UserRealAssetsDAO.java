package application.dao;

import application.model.*;

import java.util.List;

public interface UserRealAssetsDAO {

    List<Userrealassets> findAllUserrealassetsQuery();
    Userrealassets findUserrealAssetsByUserId(AppUser appUser);
    Userrealassets addUserrealassets(Userrealassets userrealassets);
    Userrealassets updateUserrealassetsHouse(Userrealassets userrealassets, House house);
    Userrealassets updateUserrealassetsPlot(Userrealassets userrealassets, Plot plot);
    Userrealassets updateUserrealassetsFlat(Userrealassets userrealassets, Flat flat);
}
