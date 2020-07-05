package application.dao;

import application.model.AppUser;
import application.model.Finance;

public interface FinanceDAO {

    Finance findByAppuseridQuery(AppUser appUserId);
    Finance updateAmount(AppUser oldAppUserId, double newAmount);
    Finance findById(int financeId);
    Finance addFinance(Finance finance);
}
