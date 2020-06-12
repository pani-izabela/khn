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
    public List<Address> findAddressByCityAndStreetAndHouseNo(Address address){
        return addressDAO.findAddressesByCityAndStreetAndHouseNo(address.getCity(), address.getStreet(), address.getHomeNumber());
    }

}
