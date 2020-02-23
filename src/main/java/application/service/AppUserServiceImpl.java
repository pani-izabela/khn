package application.service;

import application.controller.AppUserController;
import application.dao.AppUserDAO;
import application.model.AppUser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    //final static Logger LOGGER = Logger.getLogger(AppUserServiceImpl.class.getName());
    final static Logger LOGGER = Logger.getLogger(AppUserServiceImpl.class);

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
    public AppUser findById(int id) {
        return appUserDAO.findById(id);
    }

    @Override
    public AppUser addAppUser(AppUser appUser) {
        LOGGER.info("DUPA: ");
        return appUserDAO.addAppUser(appUser);
    }

    @Override
    public boolean findByEmailAndPass(String email, String pass) {
        boolean islogged = false;
        AppUser appUser = appUserDAO.findByEmailAndPassQuery(email, pass);
        if(appUser.getEmail().contains(email) && appUser.getPass().contains(pass))
            islogged = true;
        else
            islogged = false;
        return islogged;
    }

    @Override
    public AppUser findAppUserByEmailAndPass(String email, String pass) {
        AppUser appUser = appUserDAO.findByEmailAndPassQuery(email, pass);
        return appUser;
    }

    @Override
    public AppUser checkAppUserByEmail(String email) {
        AppUser appUser = appUserDAO.findByEmail(email);
        return appUser;
    }
}
