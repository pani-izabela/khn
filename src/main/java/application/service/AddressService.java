package application.service;

import application.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAddressByCityAndStreetAndHouseNo(Address address);
}
