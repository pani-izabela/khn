package application.dao;

import application.model.Address;
import application.model.AppUser;
import application.model.House;
import application.model.Plot;

import java.util.List;

public interface HouseDAO {

    List<House> findAllHousesQuery();
    House findHouseByIdQuery(int id);
    House updateAppuser(House house, AppUser appUser);
    House addHouse(House house);
    House findHouseByAddressId(Address addressId);
}
