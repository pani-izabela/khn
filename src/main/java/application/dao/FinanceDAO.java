package application.dao;

import application.model.AppUser;
import application.model.Finance;

public interface FinanceDAO {

    Finance findByAppuseridQuery(AppUser appuserid);
    Finance updateAmount(AppUser oldAppuserId, double newAmount);
}
