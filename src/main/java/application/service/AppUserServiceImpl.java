package application.service;

import application.dao.AppUserDAO;
import application.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserDAO appUserDAO;

    public AppUserServiceImpl(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    public AppUser findByIdQuery(int id) {
        return appUserDAO.findByIdQuery(id);
    }

    @Override
    public List<AppUser> findAllQuery() {
        return appUserDAO.findAllQuery();
    }

    @Override
    public AppUser addAppUser(AppUser appUser) {
        return appUserDAO.addAppUser(appUser);
    }
}
