package application.service;

import application.model.AppUser;
import org.springframework.http.ResponseEntity;

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

    ResponseEntity<String> changePassword(String email, String oldPass, String newPass);
}
