package application.service;

import application.model.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppUserService {
    AppUser findByIdQuery(int id);
    List<AppUser> findAllQuery();
    void deleteAppUser(int id, int loggedUserId);
    AppUser registerCustomerUser(AppUser appUser);
    AppUser registerSellerUser(AppUser appUser);
    AppUser loginUserFromSellerPage(AppUser appUser);
    AppUser loginUserFromCustomerPage(AppUser appUser);
    AppUser changePassword(String email, String oldPass, String newPass);
}
