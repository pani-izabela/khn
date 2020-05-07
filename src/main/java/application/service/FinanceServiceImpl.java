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

@Service
public class FinanceServiceImpl implements FinanceService {
    private FinanceDAO financeDAO;
    private AppUserService appUserService;
    private FlatDAO flatDAO;
    private HouseDAO houseDAO;

    private double amount;
    private double assetPrice;

    public FinanceServiceImpl(FinanceDAO financeDAO, AppUserService appUserService, FlatDAO flatDAO, HouseDAO houseDAO) {
        this.financeDAO = financeDAO;
        this.appUserService = appUserService;
        this.flatDAO = flatDAO;
        this.houseDAO = houseDAO;
    }


    @Override
    public Finance chcekUserAccountStatusBeforeShopping2(int id) {
        AppUser appUser = appUserService.findByIdQuery(id);
        Finance finance = financeDAO.findByAppuseridQuery(appUser);
        amount = finance.getAmount();
        return null;
    }

    @Override
    public boolean chcekUserAccountStatusBeforeShopping(int id, int assetsId, String assetsType) {
        AppUser appUser = appUserService.findByIdQuery(id);
        Finance finance = financeDAO.findByAppuseridQuery(appUser);
        amount = finance.getAmount();
        if(assetsType.equals("flat")){
            Flat flat = flatDAO.findFlatByIdQuery(assetsId);
            assetPrice = flat.getPrice();
        }
        else if(assetsType.equals("house")){
            House house = houseDAO.findHouseByIdQuery(assetsId);
            assetPrice = house.getPrice();
        }
        return amount>=assetPrice;
    }

    @Override
    public boolean chcekUserAccountStatusBeforeShopping3(int id, double totalCost) {
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

}
