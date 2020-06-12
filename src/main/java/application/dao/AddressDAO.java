package application.dao;

import application.model.Address;

import java.util.List;

public interface AddressDAO {
    List<Address> findAddressesByCityAndStreetAndHouseNo(String city, String street, String houseNo);
    Address findByCityAndStreetAndHouseNoAndType(String city, String street, String houseNo, int type);
}
