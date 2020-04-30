package application.service;

import application.dao.AppUserDAO;
import application.dao.HouseDAO;
import application.model.AppUser;
import application.model.House;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    private HouseDAO houseDAO;
    private AppUserServiceImpl appUserService;

    public HouseServiceImpl(HouseDAO houseDAO, AppUserServiceImpl appUserService) {
        this.houseDAO = houseDAO;
        this.appUserService = appUserService;
    }

    @Override
    public List<House> findAllHousesQuery() {
        return houseDAO.findAllHousesQuery();
    }

    @Override
    public House changeAppuser(int houseId, int appUserId) {
        AppUser appUser = appUserService.findByIdQuery(appUserId);
        House house = houseDAO.findHouseByIdQuery(houseId);
        return houseDAO.updateAppuser(house, appUser);
    }
}
