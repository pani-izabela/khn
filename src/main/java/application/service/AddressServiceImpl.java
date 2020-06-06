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
        /*if(!checkAddres(address.getCity(), address.getStreet(), address.getHomeNumber())){
            return addressDAO.addAddress(address);
        }
        else {
            return null;
        }*/
    }

    @Override
    public List<Address> addHouseAddress(Address address) {
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
        /*List<Address> addresses = new ArrayList<>();
        List<Address> addressesFromDB = findAddressByCityAndStreetAndHouseNo(address.getCity(), address.getStreet(), address.getHomeNumber());
        if(addressesFromDB.size()==0){
            addresses.add(addressDAO.addAddress(address));
            return addresses;
        }
        else if(addressesFromDB.size()==1 && addressesFromDB.get(0).getRealAssetsId()==3){
            Address addressHouse = addressDAO.addAddress(address);
            addresses.add(addressHouse);
            Address addressPlot = addressesFromDB.get(0);
            addresses.add(addressPlot);
            return addresses;
            //addressHouse.getId();//ten id adresu domu muszę przekazać do wiersza plot
                //plotDAO.findPlotByAddressId(addressesFromDB.get(0).getId());
            //plotDAO.updateHouseId(returnedAddress.getId());
            //addresses.add(addressFromDB);
            //return addresses;
            //czyli zwracane są dwa adresy jeden nowo dodanego domu, a drugi działki, który już w bazie był
        }
        else {
            return null;
        }*/
    }

    //-------------------metody prywatne

    private boolean checkAddres(String city, String street, String houseNo){
        boolean addressIsInDB = true;
        if(addressDAO.findByCityAndStreetAndHouseNo(city, street, houseNo) == null){
            addressIsInDB = false;
        }
        return addressIsInDB;
    }

    private List<Address> findAddressByCityAndStreetAndHouseNo(String city, String street, String houseNo){
        return addressDAO.findAddressesByCityAndStreetAndHouseNo(city, street, houseNo);
    }

    private int checkAddressType(String city, String street, String houseNo){
        Address address = addressDAO.findByCityAndStreetAndHouseNo(city, street, houseNo);
        if(address!=null){
            return address.getRealAssetsId();
        }
        else
            return 0;
    }
}
