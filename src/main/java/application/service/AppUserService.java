package application.service;

import application.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppUserService {
    AppUser findByIdQuery(int id);
    List<AppUser> findAllQuery();
    AppUser findById(int id);
    AppUser addAppUser(AppUser appUser);
    boolean findByEmailAndPass(String email, String pass);
    AppUser findAppUserByEmailAndPass(String email, String pass);
    AppUser checkAppUserByEmail(String email);
}
