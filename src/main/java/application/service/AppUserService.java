package application.service;

import application.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppUserService {
    AppUser findByIdQuery(int id);
    List<AppUser> findAllQuery();
    AppUser addAppUser(AppUser appUser);
}
