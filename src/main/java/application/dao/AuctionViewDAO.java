package application.dao;

import application.model.AuctionView;

import java.util.List;

public interface AuctionViewDAO {
    List<AuctionView> findAllQuery();
}
