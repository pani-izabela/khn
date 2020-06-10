package application.dao;

import application.model.Address;

import java.util.List;

public interface AddressDAO {
    Address addAddress(Address address);
    Address findByCityAndStreetAndHouseNo(String city, String street, String houseNo);
    List<Address> findAddressesByCityAndStreetAndHouseNo(String city, String street, String houseNo);
    Address findAddressById(int id);
    Address findByCityAndStreetAndHouseNoAndType(String city, String street, String houseNo, int type);
}
