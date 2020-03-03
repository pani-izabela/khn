package application.service;

import application.model.AppUser;
import application.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppUserService {
    //AppUser findByIdQuery(int id);
    //AppUser findById(int id);
    //AppUser addAppUser(AppUser appUser, String role);
    List<AppUser> findAllQuery();
    AppUser findAppUserByEmailAndPass(String email, String pass);
    boolean checkAppUserByEmail(String email);
    AppUser updatePass(AppUser appUser, String newPass);

    AppUser registerCustomerUser(AppUser appUser);
    AppUser registerSellerUser(AppUser appUser);

    AppUser loginUserFromSellerPage(AppUser appUser);
    AppUser loginUserFromCustomerPage(AppUser appUser);

    AppUser changePassword(String email, String oldPass, String newPass);
}
