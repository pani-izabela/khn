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
        // te 4 linijki można wyniesc do prywatnej metody
        String city = propertyToBuy.getCity();
        String postcode = propertyToBuy.getPostcode();
        String homenumber = propertyToBuy.getHomenumber();
        List<AuctionView> propertyToReturn = auctionViewDAO.findByAdress(city,postcode,homenumber);
        if(propertyToReturn.size()==2){
            totalCost = propertyToReturn.get(0).getPrice() + propertyToReturn.get(1).getPrice();
        }
        else{
            totalCost = propertyToBuy.getPrice();
        }
        return totalCost;
    }

    @Override
    // te if-y te fajnie byłoby zmniejszyć, może podział na prywatne metody, może switch?
    public AuctionView returnOtherPropertyWithTheSameAdress(String assetType, int assetId) {
        AuctionView propertyToReturn = new AuctionView();
        AuctionView propertyToBuy = auctionViewDAO.findByAssetsTypeAndAssetId(assetType, assetId);
        List<AuctionView> secondProperty = auctionViewDAO.findByAdress(
                propertyToBuy.getCity(), propertyToBuy.getPostcode(), propertyToBuy.getHomenumber()
        );
        if(secondProperty.size()==2){
            String asset_type1 = secondProperty.get(0).getAsset_type();
            String asset_type2 = secondProperty.get(1).getAsset_type();
            if (asset_type1.equals(assetType) && !asset_type2.equals(assetType)){
                propertyToReturn = secondProperty.get(1);
            }
            else if (asset_type2.equals(assetType) && !asset_type1.equals(assetType)){
                propertyToReturn = secondProperty.get(0);
            }
            return propertyToReturn;
        }
        else {
            propertyToReturn = null;
        }
        return propertyToReturn;
    }
}
