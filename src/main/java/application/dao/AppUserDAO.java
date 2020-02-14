package application.dao;

import application.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AppUserDAO {
    AppUser findByIdQuery(int id);
    List<AppUser> findAllQuery();
    AppUser addAppUser(AppUser appUser);
    AppUser findById(int id);
    void deleteById(int id);
}
