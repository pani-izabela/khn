package application.service;

import application.dao.AddressDAO;
import application.model.Address;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    public AddressServiceImpl(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    AddressDAO addressDAO;


    @Override
    public Address addPropertyAddress(Address address) {
        Address addressesFromDB = findAddressByCityAndStreetAndHouseNoAndType(address);
        if(addressesFromDB==null){
            return address;
        }
        else{
            return null;
        }
    }

    @Override
    public Address findAddressByCityAndStreetAndHouseNoAndType(Address address){
        return addressDAO.findByCityAndStreetAndHouseNoAndType(address.getCity(), address.getStreet(), address.getHomeNumber(), address.getRealAssetsId());
    }

    @Override
    public List<Address> findAddressByCityAndStreetAndHouseNo(Address address){
        return addressDAO.findAddressesByCityAndStreetAndHouseNo(address.getCity(), address.getStreet(), address.getHomeNumber());
    }


    //-------------------metody prywatne
    private List<Address> getAdressesPerType(int assetsType, Address address, Address addressFromDB){
        List<Address> addresses = new ArrayList<>();
        if(assetsType==2 && address.getRealAssetsId()==2){
            addresses = null;
        }
        else if(assetsType==3 && address.getRealAssetsId()==3){
            addresses = null;
        }
        else if(assetsType==3 && address.getRealAssetsId()==2){
            addresses.add(address);
            addresses.add(addressFromDB);
        }
        else if(assetsType==2 && address.getRealAssetsId()==3){
            addresses.add(address);
            addresses.add(addressFromDB);
        }
        return addresses;
    }
}
