package application.service;

import application.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppUserService {
    AppUser findByIdQuery(int id);
    List<AppUser> findAllQuery();
    AppUser findById(int id);
    AppUser addAppUser(AppUser appUser, String role);
    boolean findByEmailAndPass(String email, String pass);
    AppUser findAppUserByEmailAndPass(String email, String pass);
    boolean checkAppUserByEmail(String email);
    AppUser updatePass(AppUser appUser, String newPass);
}
