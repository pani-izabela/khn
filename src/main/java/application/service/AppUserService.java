package application.service;

import application.model.AppUser;
import application.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppUserService {
    //AppUser findByIdQuery(int id);
    //AppUser findById(int id);
    //boolean checkAppUserByEmail(String email);

    List<AppUser> findAllQuery();

    AppUser registerCustomerUser(AppUser appUser);
    AppUser registerSellerUser(AppUser appUser);

    AppUser loginUserFromSellerPage(AppUser appUser);
    AppUser loginUserFromCustomerPage(AppUser appUser);

    AppUser changePassword(String email, String oldPass, String newPass);
}
