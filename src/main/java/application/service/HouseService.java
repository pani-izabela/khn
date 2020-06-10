package application.service;

import application.model.Address;
import application.model.AppUser;
import application.model.House;
import application.model.Plot;

import java.util.List;

public interface HouseService {
    List<House> findAllHousesQuery();
    House changeAppuser(int houseId, int appUserId);
    House addHouse(House house);
    House findHouseById(int houseId);
    House findHouseByAddressId(Address addressId);
}
