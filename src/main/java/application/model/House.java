package application.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = House.GET_HOUSES, query = House.QUERY_GET_HOUSES),
        @NamedQuery(name = House.GET_HOUSES_BY_ID, query = House.QUERY_GET_HOUSES_BY_ID),
        @NamedQuery(name = House.GET_HOUSE_BY_ADDRESS_ID, query = House.QUERY_GET_HOUSE_BY_ADDRESS_ID)
})

@Entity
@Table(name = "HOUSE")
@Getter
@Setter
@NoArgsConstructor
public class House {

    public static final String GET_HOUSES = "House.get_houses";
    public static final String QUERY_GET_HOUSES = "select ho from House ho";

    public static final String GET_HOUSES_BY_ID = "House.get_houses_by_id";
    public static final String QUERY_GET_HOUSES_BY_ID = "select ho from House ho where ho.id = :id";

    public static final String GET_HOUSE_BY_ADDRESS_ID = "House.get_house_by_address_id";
    public static final String QUERY_GET_HOUSE_BY_ADDRESS_ID = "select ho from House ho where ho.address = :addressId";


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    private String size;

    @NotNull
    private double price;

    @NotNull
    private int rooms;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="appuser_id")
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="adress_id")
    private Address address;
}

/*INSERT INTO house (id, size, price, rooms, appuser_id, adress_id) VALUES (1, '100m', 300000.00, 3, 3, 3);*/
/*INSERT INTO house (id, size, price, rooms, appuser_id, adress_id) VALUES (2, '300m', 900000.00, 6, 3, 4);*/
/*INSERT INTO house (id, size, price, rooms, appuser_id, adress_id) VALUES (3, '270m', 100000.00, 5, 77, 5);*/
