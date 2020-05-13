package application.service;

import application.model.Finance;

public interface FinanceService {
//nza długie nazwy metod :)
    // a dwie pierwsze chyba do usunięcia? bo nie są używane
    Finance chcekUserAccountStatusBeforeShopping2(int id);
    boolean chcekUserAccountStatusBeforeShopping(int id, int assetsId, String assetsType);
    boolean chcekUserAccountStatusBeforeShopping3(int id, double totalCost);
    Finance updateAmount(int appuserId);
}
