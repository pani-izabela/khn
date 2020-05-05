package application.service;

import application.model.Userrealassets;

import java.util.List;

public interface UserrealassetsService {
    List<Userrealassets> findAllUserrealassets();
    Userrealassets addUserrealassets(int appuserId, int assetsId, String assetsType);
    Userrealassets updateUserrealassetsProperty(int appuserId, int assetsId, String assetsType);
}
