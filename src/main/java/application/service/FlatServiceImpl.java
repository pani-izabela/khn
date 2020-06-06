package application.service;

import application.dao.FlatDAO;
import application.dao.HouseDAO;
import application.model.AppUser;
import application.model.Flat;
import application.model.House;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatServiceImpl implements FlatService {

    private FlatDAO flatDAO;
    private AppUserServiceImpl appUserService;

    public FlatServiceImpl(FlatDAO flatDAO, AppUserServiceImpl appUserService) {
        this.flatDAO = flatDAO;
        this.appUserService = appUserService;
    }

    @Override
    public List<Flat> findAllFlatsQuery() {
        return flatDAO.findAllQuery();
    }

    @Override
    public Flat changeAppuser(int flatId, int appUserId) {
        AppUser appUser = appUserService.findByIdQuery(appUserId);
        Flat flat = flatDAO.findFlatByIdQuery(flatId);
        return flatDAO.updateAppuser(flat, appUser);
    }

    @Override
    public Flat addFlat(Flat flat) {
        return flatDAO.addFlat(flat);
    }
}
