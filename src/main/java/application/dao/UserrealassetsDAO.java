package application.dao;

import application.model.Userrealassets;

import java.util.List;

public interface UserrealassetsDAO {

    List<Userrealassets> findAllUserrealassetsQuery();
    Userrealassets addUserrealassets(Userrealassets userrealassets);
}
