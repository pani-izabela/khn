package application.service;

import application.model.Flat;
import application.model.House;

import java.util.List;

public interface FlatService {
    List<Flat> findAllFlatsQuery();
    Flat changeAppuser(int flatId, int appUserId);
    Flat addFlat(Flat flat);
}
