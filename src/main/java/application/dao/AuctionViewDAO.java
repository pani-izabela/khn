package application.dao;

import application.model.AuctionView;

import java.util.List;

public interface AuctionViewDAO {
    List<AuctionView> findAllQuery();
    List<AuctionView> findPropertyByAssetsTypeAndAppUserRole(String type, int appUserRole);
    AuctionView findByAssetsTypeAndAssetId(String assetType, int assetId);
    List<AuctionView> findByAdress(String city, String postcode, String homenumber);
    List<AuctionView> findPropertyByAppUserIdAndCustomerRole(int appUserId);
    List<AuctionView> findPropertyByAppUserIdAndSellerRole(int appUserId);
}
