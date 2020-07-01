package application.service;

import application.model.AuctionView;

import java.util.List;

public interface MyPropertiesService {
    List<AuctionView> getAllProperties();
    List<AuctionView> getPropertiesForCustomer(int appUserId);
    List<AuctionView> getPropertiesForSeller(int appUserId);
}
