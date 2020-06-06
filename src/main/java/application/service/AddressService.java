package application.service;

import application.model.Address;

import java.util.List;

public interface AddressService {

    Address addFlatAddress(Address address);
    List<Address> addHouseAddress(Address address);
}
