package application.service;

import application.model.Userrealassets;

public interface UserrealassetsService {
    Userrealassets addUserrealassets(int appuserId, int assetsId, String assetsType);
}
