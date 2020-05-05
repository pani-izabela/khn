package application.dao;

import application.model.AuctionView;

import java.util.List;

public interface AuctionViewDAO {
    List<AuctionView> findAllQuery();
    List<AuctionView> findAllQueryByAssetsType(String type);
    List<AuctionView> findPropertyByAssetsTypeAndAppuserRole(String type, int appuserRole);
    AuctionView findByAssetsTypeAndAssetId(String assetType, int assetId);
    //AuctionView findByAdress(String city, String postcode, String homenumber, String assetType);
    List<AuctionView> findByAdress(String city, String postcode, String homenumber);
    void deleteById(int id);
}
