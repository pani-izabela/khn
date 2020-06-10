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
    public List<Address> addHouseAddress(Address address) {
        List<Address> addresses = new ArrayList<>();
        List<Address> addressesFromDB = findAddressByCityAndStreetAndHouseNo(address.getCity(), address.getStreet(), address.getHomeNumber());
        if(addressesFromDB.size() == 0){
            addresses.add(address);
        }
        else if(addressesFromDB.size() == 1){
            int assetsType = addressesFromDB.get(0).getRealAssetsId();
            addresses = getAdressesPerType(assetsType, address, addressesFromDB.get(0));
        }
        else if(addressesFromDB.size() == 2){
            addresses = null;
        }
        return addresses;
    }

    @Override
    public Address addPropertyAddress(Address address) {
        Address addressesFromDB = findAddressByCityAndStreetAndHouseNoAndType(
                address.getCity(),
                address.getStreet(),
                address.getHomeNumber(),
                address.getRealAssetsId());
        if(addressesFromDB==null){
            return address;
        }
        else{
            return null;
        }
    }

    //-------------------metody prywatne

    private List<Address> findAddressByCityAndStreetAndHouseNo(String city, String street, String houseNo){
        return addressDAO.findAddressesByCityAndStreetAndHouseNo(city, street, houseNo);
    }

    private Address findAddressByCityAndStreetAndHouseNoAndType(String city, String street, String houseNo, int type){
        return addressDAO.findByCityAndStreetAndHouseNoAndType(city, street, houseNo, type);
    }

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
