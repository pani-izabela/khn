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
    public Address addFlatAddress(Address address) {
        List<Address> addressesFromDB = findAddressByCityAndStreetAndHouseNo(address.getCity(), address.getStreet(), address.getHomeNumber());
        if(!addressesFromDB.isEmpty()){
            return addressesFromDB.get(0);
        }
        else{
            Address address1 = address;
            return address1;
        }
    }

    @Override
    public List<Address> addHouseAddress(Address address) {
        List<Address> addresses = new ArrayList<>();
        List<Address> addressesFromDB = findAddressByCityAndStreetAndHouseNo(address.getCity(), address.getStreet(), address.getHomeNumber());
        if(addressesFromDB.size() == 0){
            Address houseAddress = addressDAO.addAddress(address);
            addresses.add(houseAddress);
        }
        else if(addressesFromDB.size() == 1){
            int assetsType = addressesFromDB.get(0).getRealAssetsId();
            Address houseAddress = addressDAO.addAddress(address);
            addresses = getAdressesPerType(assetsType, houseAddress, addressesFromDB.get(0));
        }
        else if(addressesFromDB.size() == 2){
            addresses = null;
        }
        return addresses;
    }

    @Override
    public List<Address> addPlotAddress(Address address) {
        List<Address> addresses = new ArrayList<>();
        List<Address> addressesFromDB = findAddressByCityAndStreetAndHouseNo(address.getCity(), address.getStreet(), address.getHomeNumber());
        if(!addressesFromDB.isEmpty()){
            addresses.add(addressesFromDB.get(0));
            return addresses;
        }
        else{
            addresses.add(address);
            return addresses;
        }
    }

    @Override
    public Address findAddressById(int addressId) {
        return addressDAO.findAddressById(addressId);
    }

    //-------------------metody prywatne

    private List<Address> findAddressByCityAndStreetAndHouseNo(String city, String street, String houseNo){
        return addressDAO.findAddressesByCityAndStreetAndHouseNo(city, street, houseNo);
    }

    private List<Address> getAdressesPerType(int assetsType, Address houseAddress, Address plotAddress){
        List<Address> addresses = new ArrayList<>();
        if(assetsType==2){
            addresses = null;
        }
        else if(assetsType==3){
            addresses.add(houseAddress);
            addresses.add(plotAddress);
        }
        return addresses;
    }
}
