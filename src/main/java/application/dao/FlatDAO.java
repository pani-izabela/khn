package application.dao;

import application.model.AppUser;
import application.model.Flat;
import application.model.House;

import java.util.List;

public interface FlatDAO {

    List<Flat> findAllQuery();
    Flat findFlatByIdQuery(int id);
    Flat updateAppuser(Flat flat, AppUser appUser);
}
