package application.service;

import application.components.Enums;
import application.dao.AppUserDAO;
import application.model.AppUser;
import application.model.Role;
//import org.apache.log4j.Logger;
//import org.slf4j.event.Level;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    //final static Logger LOGGER = Logger.getLogger(AppUserServiceImpl.class.getName());
    //final static Logger LOGGER = Logger.getLogger(AppUserServiceImpl.class);
    protected final Logger log = Logger.getLogger(getClass().getName());

    private AppUserDAO appUserDAO;


    public AppUserServiceImpl(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    /*@Override
    public AppUser findByIdQuery(int id) {return appUserDAO.findByIdQuery(id);}

     @Override
    public AppUser findById(int id) {return appUserDAO.findById(id);}*/

    @Override
    public List<AppUser> findAllQuery() {
        return appUserDAO.findAllQuery();
    }

    //-------------------------------rejestracja-----------------------------------------
    @Override
    public AppUser registerCustomerUser(AppUser appUser) {
        log.log(Level.INFO, "Zarejestrował sie uzytkownik o emailu: " + appUser.getEmail());
        AppUser appUserToReturn;
        if (!checkAppUserByEmail(appUser.getEmail())) {
            appUser.setRoles(addRoleForUser(1, Enums.CUSTOMER));
            appUserToReturn = appUserDAO.addAppUser(appUser);
        } else {
            return null;
        }
        return appUserToReturn;

    }

    @Override
    public AppUser registerSellerUser(AppUser appUser) {
        AppUser appUserToReturn = new AppUser();
        if (!checkAppUserByEmail(appUser.getEmail())) {
            appUser.setRoles(addRoleForUser(2, Enums.SELLER));
            appUserToReturn = appUserDAO.addAppUser(appUser);
        } else {//sprawdza, czy nie ma już roli customer, bo customer nie może być seller
            List<Integer> userRolesIdList = getUserRoles(findAppUserByEmail(appUser));
            for (Integer roleId : userRolesIdList) {
                if (roleId == 1)
                    return null;
            }
        }
        return appUserToReturn;
    }

    //-----------------------------logowanie----------------------------------------------
    @Override
    public AppUser loginUserFromSellerPage(AppUser appUser) {
        AppUser appUserToReturn = new AppUser();
        AppUser appUserFromDb = findAppUserByEmailAndPass(appUser.getEmail(), appUser.getPass());
        List<Integer> userRolesIdList = getUserRoles(appUserFromDb);
        for (Integer roleId : userRolesIdList) {
            if (roleId == 2 || roleId == 3)
                appUserToReturn = appUserFromDb;
            else
                return null;
        }
        return appUserToReturn;
    }

    @Override
    public AppUser loginUserFromCustomerPage(AppUser appUser) {
        //nie wiem, czy dobrze robię, że go tu inicjuję, a może lepiej nullem zainicjować;
        AppUser appUserToReturn = new AppUser();
        AppUser appUserFromDb = findAppUserByEmailAndPass(appUser.getEmail(), appUser.getPass());
        List<Integer> userRolesIdList = getUserRoles(appUserFromDb);
        for (Integer roleId : userRolesIdList) {
            if (roleId == 1 || roleId == 3)
                appUserToReturn = appUserFromDb;
            else
                return null;
        }
        return appUserToReturn;
    }

    //--------------------------zmiana hasła-----------------------------------------


    @Override
    public AppUser changePassword(String email, String oldPass, String newPass) {
        AppUser appUserFromDb = findAppUserByEmailAndPass(email, oldPass);
        if(appUserFromDb != null && (!newPass.equals(oldPass)) && (!newPass.isEmpty())) {
            return updatePass(appUserFromDb, newPass);
        }
        else
            return null;
    }

    //--------------------------------------- metody prywatne ---------------------------------------
    private AppUser findAppUserByEmail(AppUser appUser) {
        return appUserDAO.findByEmail(appUser.getEmail());
    }

    private List<Integer> getUserRoles(AppUser appUser) {
        List<Role> userRoles = appUser.getRoles();
        List<Integer> userRolesIdList = new ArrayList<>();
        for (Role role : userRoles) {
            userRolesIdList.add(role.getId());
        }
        return userRolesIdList;
    }

    private List<Role> addRoleForUser(int roleId, String roleName) {
        List<Role> roles = new ArrayList<>();
        Role roleSeller = new Role();
        roleSeller.setId(roleId);
        roleSeller.setName(roleName);
        roles.add(roleSeller);
        return roles;
    }

    private AppUser updatePass(AppUser appUser, String newPass) {
        return appUserDAO.updatePass(appUser, newPass);
    }

    private AppUser findAppUserByEmailAndPass(String email, String pass) {
        AppUser appUserFromDB = appUserDAO.findByEmailAndPassQuery(email, pass);
        if(appUserFromDB==null)
            return null;
        AppUser appUserToReturn = new AppUser();
        if (appUserFromDB.getEmail().contains(email) && appUserFromDB.getPass().contains(pass))
            appUserToReturn = appUserFromDB;
        return appUserToReturn;
    }

    private boolean checkAppUserByEmail(String email) {
        boolean appUserInDB = true;
        if (appUserDAO.findByEmail(email) == null)
            appUserInDB = false;
        return appUserInDB;
    }

}
