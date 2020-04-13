package application.service;

import application.dao.HouseDAO;
import application.model.House;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    private HouseDAO houseDAO;

    public HouseServiceImpl(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

    @Override
    public List<House> findAllHousesQuery() {
        return houseDAO.findAllHousesQuery();
    }
}
