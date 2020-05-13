package application.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = House.GET_HOUSES, query = House.QUERY_GET_HOUSES),
        @NamedQuery(name = House.GET_HOUSES_BY_ID, query = House.QUERY_GET_HOUSES_BY_ID)
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
    //appUser lub app_user
    private AppUser appuser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="adress_id")
    private Adress adress;
}

/*INSERT INTO house (id, size, price, rooms, appuser_id, adress_id) VALUES (1, '100m', 300000.00, 3, 3, 3);*/
/*INSERT INTO house (id, size, price, rooms, appuser_id, adress_id) VALUES (2, '300m', 900000.00, 6, 3, 4);*/
/*INSERT INTO house (id, size, price, rooms, appuser_id, adress_id) VALUES (3, '270m', 100000.00, 5, 77, 5);*/
