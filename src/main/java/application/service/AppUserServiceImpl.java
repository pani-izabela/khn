package application.service;

import application.controller.AppUserController;
import application.dao.AppUserDAO;
import application.model.AppUser;
import application.model.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
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
        AppUser appUserToReturn = null;

        boolean appUserHaveSellerRole = false;
        AppUser appUserFromDB = findAppUserByEmailAndPass(appUser.getEmail(), appUser.getPass());
        if(appUserFromDB!=null && appUserFromDB.getRoles().size()>0){
            List<Role> usersRole = appUserFromDB.getRoles();
            for (Role role1 : usersRole) {
                int usersRoleId = role1.getId();
                if(usersRoleId==1){
                    appUserHaveSellerRole = true;
                }
            }
        }

        if(role.equals("customer") && appUserHaveSellerRole==false){
            roleCustomer.setId(1);
            roleCustomer.setName(role);
        }
        else if(role.equals("seller")){
            roleCustomer.setId(2);
            roleCustomer.setName(role);
        }
        roles.add(roleCustomer);
        appUser.setRoles(roles);
        if(appUserFromDB==null)
            appUserToReturn = appUserDAO.addAppUser(appUser);
        //if(appUserFromDB!=null && role.equals("customer")==false && appUserHaveSellerRole==false)

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

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public AppUser findAppUserByEmailAndPass(String email, String pass) {
        AppUser appUserFromDB = appUserDAO.findByEmailAndPassQuery(email, pass);
        AppUser appUserToReturn = new AppUser();
        try {
            if(appUserFromDB.getEmail().contains(email) && appUserFromDB.getPass().contains(pass))
                appUserToReturn = appUserFromDB;
        }
        catch (Exception e){
            appUserToReturn = null;
        }
        return appUserToReturn;
    }

    private List<Integer> getUserRoles(AppUser appUser){
        List<Role> userRoles = appUser.getRoles();
        List<Integer> userRolesIdList = new ArrayList<>();
        for (Role role : userRoles) {
            userRolesIdList.add(role.getId());
        }
        return userRolesIdList;
    }

    @Override
    public AppUser loginUserFromSellerPage(AppUser appUser) {
        AppUser appUserToReturn = new AppUser();
        try{
            AppUser appUserFromDb = findAppUserByEmailAndPass(appUser.getEmail(), appUser.getPass());
            List<Integer> userRolesIdList = getUserRoles(appUserFromDb);
            for(Integer roleId : userRolesIdList){
                if(roleId==2 || roleId==3)
                    appUserToReturn = appUserFromDb;
                else
                    return null;
            }
        }
        catch(NoResultException e){
            return null;
        }
        return appUserToReturn;
    }

    @Override
    public AppUser loginUserFromCustomerPage(AppUser appUser) {
        //nie wiem, czy dobrze robię, że go tu inicjuję;
        AppUser appUserToReturn = new AppUser();
        try{
            AppUser appUserFromDb = findAppUserByEmailAndPass(appUser.getEmail(), appUser.getPass());
            List<Integer> userRolesIdList = getUserRoles(appUserFromDb);
            for(Integer roleId : userRolesIdList){
                if(roleId==1 || roleId==3)
                    appUserToReturn = appUserFromDb;
                else
                    return null;
            }
        }
        catch(NoResultException e){
            return null;
        }
        return appUserToReturn;
    }

}
