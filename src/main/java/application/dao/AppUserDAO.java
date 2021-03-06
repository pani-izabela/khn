package application.dao;

import application.model.AppUser;
import application.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AppUserDAO {
    AppUser findByIdQuery(int id);
    List<AppUser> findAllQuery();
    AppUser addAppUser(AppUser appUser);
    AppUser findById(int id);
    void deleteById(int id);
    AppUser findByEmailAndPassQuery(String email, String pass);
    AppUser findByEmail(String email);
    AppUser updatePass(AppUser appUser, String newPass);
    AppUser updateAppUser(AppUser oldUser, AppUser newUser);
    AppUser updateUserRole(AppUser appUser, List<Role> rolesList);

}
