package application.wrapper;

import application.model.Address;
import application.model.House;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseWrapper {
    private Address address;
    private House house;
}
