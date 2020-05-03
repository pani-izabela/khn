package application.dao;

import application.model.AuctionView;

import java.util.List;

public interface AuctionViewDAO {
    List<AuctionView> findAllQuery();
    List<AuctionView> findAllQueryByAssetsType(String type);
    List<AuctionView> findPropertyByAssetsTypeAndAppuserRole(String type, int appuserRole);
    void deleteById(int id);
}
