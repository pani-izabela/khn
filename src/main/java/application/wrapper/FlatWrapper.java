package application.wrapper;

import application.model.Address;
import application.model.Flat;
import lombok.Getter;
import lombok.Setter;

public class FlatWrapper {
    private Address address;
    private Flat flat;

    public Address getAddress() {
        return address;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }
}
