package application.dao;

import application.model.AuctionView;

import java.util.List;

public interface AuctionViewDAO {
    List<AuctionView> findAllQuery();
    List<AuctionView> findPropertyByAssetsTypeAndAppUserRole(String type, int appUserRole);
    AuctionView findByAssetsTypeAndAssetId(String assetType, int assetId);
    List<AuctionView> findByAdress(String city, String postcode, String homenumber);
    void deleteById(int id);
    List<AuctionView> findPropertiesByType(String assetType);
}
