package application.service;

import application.model.Finance;

public interface FinanceService {

    Finance chcekUserAccountStatusBeforeShopping2(int id);
    boolean chcekUserAccountStatusBeforeShopping(int id, int assetsId, String assetsType);
    Finance updateAmount(int appuserId);
}
