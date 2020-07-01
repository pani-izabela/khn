package application.service;

import application.dao.AppUserDAO;
import application.dao.FinanceDAO;
import application.dao.FlatDAO;
import application.dao.HouseDAO;
import application.model.AppUser;
import application.model.Finance;
import application.model.Flat;
import application.model.House;
import org.springframework.stereotype.Service;

@Service("financeService")
public class FinanceServiceImpl implements FinanceService {
    private FinanceDAO financeDAO;
    private AppUserService appUserService;

    private double amount;
    private double assetPrice;

    public FinanceServiceImpl(FinanceDAO financeDAO, AppUserService appUserService) {
        this.financeDAO = financeDAO;
        this.appUserService = appUserService;
    }

    @Override
    public boolean chcekUserAccountStatus(int id, double totalCost) {
        AppUser appUser = appUserService.findByIdQuery(id);
        Finance finance = financeDAO.findByAppuseridQuery(appUser);
        amount = finance.getAmount();
        assetPrice = totalCost;
        return amount>=assetPrice;
    }

    @Override
    public Finance updateAmount(int appuserId) {
        AppUser appUser = appUserService.findByIdQuery(appuserId);
        double newAmount = amount - assetPrice;
        return financeDAO.updateAmount(appUser, newAmount);
    }

    @Override
    public double getAmountByAppUserId(int appUserId) {
        Finance finance = financeDAO.findByAppuseridQuery(appUserService.findByIdQuery(appUserId));
        return finance.getAmount();
    }

}
