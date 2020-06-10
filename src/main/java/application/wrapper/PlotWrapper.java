package application.wrapper;

import application.model.Address;
import application.model.House;
import application.model.Plot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlotWrapper {
    private Address address;
    private Plot plot;
}
