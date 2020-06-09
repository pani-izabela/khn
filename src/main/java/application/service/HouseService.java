package application.service;

import application.model.AppUser;
import application.model.House;

import java.util.List;

public interface HouseService {
    List<House> findAllHousesQuery();
    House changeAppuser(int houseId, int appUserId);
    House addHouse(House house);
    House findHouseById(int houseId);
}
