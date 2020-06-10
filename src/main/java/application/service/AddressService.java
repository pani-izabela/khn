package application.service;

import application.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> addHouseAddress(Address address);
    Address addPropertyAddress(Address address);
}
