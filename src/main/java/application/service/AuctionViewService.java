package application.service;

import application.model.AppUser;
import application.model.AuctionView;

import java.util.List;

public interface AuctionViewService {
    double returnTotalCost(String assetType, int assetId);
    // za długa nazwa :)
    AuctionView returnOtherPropertyWithTheSameAdress(String assetType, int assetId);
}
