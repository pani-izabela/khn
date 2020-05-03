package application.service;

import application.dao.FlatDAO;
import application.dao.HouseDAO;
import application.dao.UserrealassetsDAO;
import application.model.AppUser;
import application.model.Flat;
import application.model.House;
import application.model.Userrealassets;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserrealassetsServiceImpl implements UserrealassetsService{

    private AppUserService appUserService;
    private FlatDAO flatDAO;
    private HouseDAO houseDAO;
    private UserrealassetsDAO userrealassetsDAO;

    public UserrealassetsServiceImpl(AppUserService appUserService, FlatDAO flatDAO, HouseDAO houseDAO, UserrealassetsDAO userrealassetsDAO) {
        this.appUserService = appUserService;
        this.flatDAO = flatDAO;
        this.houseDAO = houseDAO;
        this.userrealassetsDAO = userrealassetsDAO;
    }

    @Override
    public List<Userrealassets> findAllUserrealassets() {
        return userrealassetsDAO.findAllUserrealassetsQuery();
    }

    @Override
    public Userrealassets addUserrealassets(int appuserId, int assetsId, String assetsType) {
        Userrealassets userrealassets = new Userrealassets();
        AppUser appUser = appUserService.findByIdQuery(appuserId);
        userrealassets.setAppUser(appUser);
        if(assetsType.equals("flat")){
            Flat flat = flatDAO.findFlatByIdQuery(assetsId);
            userrealassets.setFlat(flat);
        }
        else if(assetsType.equals("house")){
            House house = houseDAO.findHouseByIdQuery(assetsId);
            userrealassets.setHouse(house);
        }
        return userrealassetsDAO.addUserrealassets(userrealassets);
    }
}
