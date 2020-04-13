package application.dao;

import application.model.House;

import java.util.List;

public interface HouseDAO {

    List<House> findAllHousesQuery();
}
