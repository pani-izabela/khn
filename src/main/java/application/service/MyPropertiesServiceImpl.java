package application.service;

import application.dao.AuctionViewDAO;
import application.model.AuctionView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myPropertiesService")
public class MyPropertiesServiceImpl implements MyPropertiesService {

    private AuctionViewDAO auctionViewDAO;

    public MyPropertiesServiceImpl(AuctionViewDAO auctionViewDAO) {
        this.auctionViewDAO = auctionViewDAO;
    }

    @Override
    public List<AuctionView> getAllProperties() {
        return auctionViewDAO.findAllQuery();
    }
}
