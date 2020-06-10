package application.wrapper;

import application.model.Address;
import application.model.Flat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlatWrapper {
    private Address address;
    private Flat flat;

}
