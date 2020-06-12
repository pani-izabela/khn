package application.service;

import application.model.Address;

import java.util.List;

public interface AddressService {

    Address addPropertyAddress(Address address);
    Address findAddressByCityAndStreetAndHouseNoAndType(Address address);
    List<Address> findAddressByCityAndStreetAndHouseNo(Address address);
}
