package application.service;

import application.model.Finance;

public interface FinanceService {

    boolean chcekUserAccountStatus(int id, double totalCost);
    Finance updateAmount(int appuserId);
}
