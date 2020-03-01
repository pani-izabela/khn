package application.service;

import application.model.AppUser;
import application.model.Role;
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
    AppUser loginUserFromSellerPage(String email, String pass);
    AppUser loginUserFromCustomerPage(String email, String pass);
}
