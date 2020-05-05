package application.service;

import application.model.Finance;

public interface FinanceService {

    Finance chcekUserAccountStatusBeforeShopping2(int id);
    boolean chcekUserAccountStatusBeforeShopping(int id, int assetsId, String assetsType);
    boolean chcekUserAccountStatusBeforeShopping3(int id, double totalCost);
    Finance updateAmount(int appuserId);
    Finance update(int id);
}
