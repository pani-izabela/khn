package application.dao;

import application.model.AppUser;
import application.model.House;

import java.util.List;

public interface HouseDAO {

    List<House> findAllHousesQuery();
    House findHouseByIdQuery(int id);
    House updateAppuser(House house, AppUser appUser);
}
