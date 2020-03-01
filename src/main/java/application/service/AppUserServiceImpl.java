package application.service;

import application.controller.AppUserController;
import application.dao.AppUserDAO;
import application.model.AppUser;
import application.model.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public AppUser addAppUser(AppUser appUser, String role) {
        LOGGER.info("DUPA: ");
        List<Role> roles = new ArrayList<>();
        Role roleCustomer = new Role();
        if(role.equals("customer")){//i jeśli użytkownik nie ma już roli seller - dopisać)
            roleCustomer.setId(1);
            roleCustomer.setName(role);
        }
        else if(role.equals("seller")){
            roleCustomer.setId(2);
            roleCustomer.setName(role);
        }
        roles.add(roleCustomer);
        appUser.setRoles(roles);
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
        AppUser appUserFromDB = appUserDAO.findByEmailAndPassQuery(email, pass);
        AppUser appUserToReturn = new AppUser();
        if(appUserFromDB.getEmail().contains(email) && appUserFromDB.getPass().contains(pass))
            appUserToReturn = appUserFromDB;
        return appUserToReturn;
    }

    @Override
    public boolean checkAppUserByEmail(String email) {
        boolean appUserInDB = true;
        if(appUserDAO.findByEmail(email) == null)
            appUserInDB = false;
        return appUserInDB;
    }

    @Override
    public AppUser updatePass(AppUser appUser, String newPass) {
        return appUserDAO.updatePass(appUser, newPass);
    }

    @Override
    public AppUser loginUserFromSellerPage(String email, String pass) {
        AppUser appUserFromDb = findAppUserByEmailAndPass(email, pass);
        AppUser appUserToReturn = new AppUser();

        if(appUserFromDb!=null){
            int usersRoleId = 0;

            List<Role> userRoles = appUserFromDb.getRoles();
            for (Role role : userRoles) {
                usersRoleId = role.getId();
            }

            if ((usersRoleId == 2) || (usersRoleId == 3))
                appUserToReturn = appUserFromDb;
            else
                appUserToReturn = null;
        }
        return appUserToReturn;
    }

    @Override
    public AppUser loginUserFromCustomerPage(String email, String pass) {
        AppUser appUserFromDb = findAppUserByEmailAndPass(email, pass);
        AppUser appUserToReturn = new AppUser();

        if(appUserFromDb!=null){
            int usersRoleId = 0;

            List<Role> userRoles = appUserFromDb.getRoles();
            for (Role role : userRoles) {
                usersRoleId = role.getId();
            }

            if ((usersRoleId == 1) || (usersRoleId == 3))
                appUserToReturn = appUserFromDb;
            else
                appUserToReturn = null;
        }
        return appUserToReturn;
    }

}
