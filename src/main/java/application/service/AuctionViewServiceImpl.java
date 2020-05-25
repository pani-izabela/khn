package application.service;

import application.dao.AuctionViewDAO;
import application.model.AuctionView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionViewServiceImpl implements AuctionViewService {

    private AuctionViewDAO auctionViewDAO;

    public AuctionViewServiceImpl(AuctionViewDAO auctionViewDAO) {
        this.auctionViewDAO = auctionViewDAO;
    }

    @Override
    public double returnTotalCost(String assetType, int assetId) {
        double totalCost;
        AuctionView propertyToBuy = auctionViewDAO.findByAssetsTypeAndAssetId(assetType, assetId);
        List<AuctionView> propertyToReturn = getPropertiesByAddress(propertyToBuy);
        if(propertyToReturn.size()==2){
            totalCost = propertyToReturn.get(0).getPrice() + propertyToReturn.get(1).getPrice();
        }
        else{
            totalCost = propertyToBuy.getPrice();
        }
        return totalCost;
    }

    @Override
    public AuctionView getPropertyWithTheSameAddress(String assetType, int assetId) {
        AuctionView propertyToBuy = auctionViewDAO.findByAssetsTypeAndAssetId(assetType, assetId);
        List<AuctionView> secondProperty = getPropertiesByAddress(propertyToBuy);
        if(secondProperty.size()==2){
            return getProperty(secondProperty, assetType);
        }
        else {
            return null;
        }
    }


    //---------------prywatne metody

    private List<AuctionView> getPropertiesByAddress(AuctionView auctionView){
        String city = auctionView.getCity();
        String postcode = auctionView.getPostcode();
        String homenumber = auctionView.getHomenumber();
        return auctionViewDAO.findByAdress(city,postcode,homenumber);
    }

    private AuctionView getProperty(List<AuctionView> auctionViewList, String assetType){
        AuctionView propertyToReturn = new AuctionView();
        boolean asset_type1 = auctionViewList.get(0).getAsset_type().equals(assetType);
        boolean asset_type2 = auctionViewList.get(1).getAsset_type().equals(assetType);
        if(asset_type1 && !asset_type2){
            propertyToReturn = auctionViewList.get(1);
        }
        else if(asset_type2 && !asset_type1){
            propertyToReturn = auctionViewList.get(0);
        }
        return propertyToReturn;
    }
}
