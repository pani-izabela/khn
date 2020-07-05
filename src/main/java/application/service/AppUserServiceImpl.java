package application.service;

import application.components.Enums;
import application.dao.AppUserDAO;
import application.dao.FinanceDAO;
import application.model.AppUser;
import application.model.Finance;
import application.model.Role;
//import org.apache.log4j.Logger;
//import org.slf4j.event.Level;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService {
    //final static Logger LOGGER = Logger.getLogger(AppUserServiceImpl.class.getName());
    //final static Logger LOGGER = Logger.getLogger(AppUserServiceImpl.class);
    protected final Logger log = Logger.getLogger(getClass().getName());

    private AppUserDAO appUserDAO;
    private FinanceDAO financeDAO;

    public int loggedCustomerId;
    public int loggedSellerId;


    public AppUserServiceImpl(AppUserDAO appUserDAO, FinanceDAO financeDAO) {
        this.appUserDAO = appUserDAO;
        this.financeDAO = financeDAO;
    }

    @Override
    public AppUser findByIdQuery(int id) {return appUserDAO.findByIdQuery(id);}

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
            addFinance(appUserToReturn);
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
            if(isCustomer(appUser)) return null;
        }
        return appUserToReturn;
    }

    //-----------------------------logowanie----------------------------------------------
    @Override
    public AppUser loginUserFromSellerPage(AppUser appUser) {
        AppUser appUserFromDb = findAppUserByEmailAndPass(appUser.getEmail(), appUser.getPass());
        if(isSeller(appUserFromDb) || isAdmin(appUser)) {
            loggedSellerId = appUserFromDb.getId();
            return appUserFromDb;
        }
        else
            return null;
    }

    @Override
    public AppUser loginUserFromCustomerPage(AppUser appUser) {
        AppUser appUserFromDb = findAppUserByEmailAndPass(appUser.getEmail(), appUser.getPass());
        if(isCustomer(appUserFromDb) || isAdmin(appUserFromDb)){
            loggedCustomerId = appUserFromDb.getId();
            return appUserFromDb;
        }
        else
            return null;
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

    //---------------------------------------- zmiana danych użytkownika --------------

    @Override
    public AppUser changeDataOfUser(AppUser newUser) {
        AppUser appUserFromDb = findByIdQuery(newUser.getId());
        if(appUserFromDb != null){
            return appUserDAO.updateAppUser(appUserFromDb, newUser);
        }
        else
            return null;
    }

    //----------------------------------dodanie nowej roli użytkownikowi-----------------------

    @Override
    public AppUser addCustomerRole(AppUser appUser) {
        AppUser appUserFromDb = findByIdQuery(appUser.getId());
        if(appUserFromDb != null){
            addFinance(appUserFromDb);
            AppUser appUserToReturn = appUserDAO.updateUserRole(appUserFromDb, addAdditionalRole(1, Enums.CUSTOMER, appUser));
            loggedCustomerId = appUserToReturn.getId();
            return appUserToReturn;
        }
        else
            return null;
    }

    //----------------------------------------usuwanie użytkownika przez admina --------------

    @Override
    public void deleteAppUser(int id, int loggedUserId){
        appUserDAO.deleteById(id);
    }

    @Override
    public int getLoggedCustomerId() {
        return loggedCustomerId;
    }

    @Override
    public int getLoggedSellerId() {
        return loggedSellerId;
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

    private List<Role> addAdditionalRole(int roleId, String roleName, AppUser appUser){
        AppUser appUserFromDB = findByIdQuery(appUser.getId());
        List<Role> roles1 = appUserFromDB.getRoles();
        Role role = new Role();
        role.setId(roleId);
        role.setName(roleName);
        roles1.add(role);
        return roles1;
    }

    private AppUser updatePass(AppUser appUser, String newPass) {
        return appUserDAO.updatePass(appUser, newPass);
    }

    private AppUser findAppUserByEmailAndPass(String email, String pass) {
        AppUser appUserFromDB = appUserDAO.findByEmailAndPassQuery(email, pass);
        if (appUserFromDB.getEmail().contains(email) && appUserFromDB.getPass().contains(pass)){
            return appUserFromDB;
        }
        else {
            return null;
        }
    }

    private boolean checkAppUserByEmail(String email) {
        boolean appUserInDB = true;
        if (appUserDAO.findByEmail(email) == null)
            appUserInDB = false;
        return appUserInDB;
    }

    private boolean isCustomer(AppUser appUser){
        List<Integer> userRolesIdList = getUserRoles(findAppUserByEmail(appUser));
        for (Integer roleId : userRolesIdList) {
            if (roleId == 1)
                return true;
        }
        return false;
    }

    private boolean isSeller(AppUser appUser){
        List<Integer> userRolesIdList = getUserRoles(findAppUserByEmail(appUser));
        for (Integer roleId : userRolesIdList) {
            if (roleId == 2)
                return true;
        }
        return false;
    }

    private boolean isAdmin(AppUser appUser){
        List<Integer> userRolesIdList = getUserRoles(findAppUserByEmail(appUser));
        for (Integer roleId : userRolesIdList) {
            if (roleId == 3)
                return true;
        }
        return false;
    }

    private void addFinance(AppUser appUser){
        Finance finance = new Finance();
        finance.setAmount(1000000.00);
        finance.setCurrency("PLN");
        finance.setAppUser(appUserDAO.findByIdQuery(appUser.getId()));
        financeDAO.addFinance(finance);
    }

}
