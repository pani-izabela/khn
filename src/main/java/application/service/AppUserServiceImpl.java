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

    //Rozdzielilabym to na oddzielna metode dla customera i sellera
    // Dodatkowo zrobilabym prywatna metode do dodawania odpowiedniej roli
    // Jesli potrzebne bedzie jeszcze skorzystanie z pobrania roli uzytkownikow to skorzystac z prywatnej metody, ktora zostala opisana przy  komentarzu dla loginUserFromSellerPage
    // aczkolwiek podczas rejestracji raczej nie musiomy sprawdzac rol, tylko nadac :)

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



    // ta metodka nie jest uzywana
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

    // tak jak napisalam w kontrolerze do zmiany hasla, mozna by to uporzadkowac
    //
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

    // ta tez moze byc prywatna i zabrana z UniqueEmailValidator
    @Override
    public boolean checkAppUserByEmail(String email) {
        boolean appUserInDB = true;
        if(appUserDAO.findByEmail(email) == null)
            appUserInDB = false;
        return appUserInDB;
    }

    @Override
    //to jako prywatna metoda
    public AppUser updatePass(AppUser appUser, String newPass) {
        return appUserDAO.updatePass(appUser, newPass);
    }



    /* Odpowierdz na pytanie nr 1
    Możesz miec takie metody :)

    Tylko ja bym to zrobila troszke inaczej

    W kontrolerze uderzam na prywatna metode do logowania
    Metoda do logowania posiada prywatna metode, ktora sprawdza czy user istnieje i kolejna prywatna metode, ktora sprawdza role uzytkownikow
    Zaleznie od wyniku tej metody pusci uzytkownika by sie zalogowal lub nie

    Jesli mamy jakas metode w serwisie, ktora jest wykorzystywana tylko w nim to robimy ja prywatna (czesto to sa metody, ktorw wykorzystujemy kilka razy w tym serwisie)
    Tak wiec te dwie metody ponizej bym porozbijala tak jak napisalam wyzej

    */


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
