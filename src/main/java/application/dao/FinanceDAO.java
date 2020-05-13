package application.dao;

import application.model.AppUser;
import application.model.Finance;

public interface FinanceDAO {
// nazewnictwo
    // appUserId lub app_user_id  < - w sumie uwaga do każdego appuserid  :)
    Finance findByAppuseridQuery(AppUser appuserid);
    Finance updateAmount(AppUser oldAppuserId, double newAmount);
    Finance findById(int financeId);
}
