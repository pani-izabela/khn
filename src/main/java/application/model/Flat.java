package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Flat.GET_FLATS, query = Flat.QUERY_GET_FLATS),
        @NamedQuery(name = Flat.GET_FLATS_BY_ID, query = Flat.QUERY_GET_FLATS_BY_ID)
})

@Entity
@Table(name = "FLAT")
@Getter
@Setter
@NoArgsConstructor
public class Flat {

    public static final String GET_FLATS = "Flat.get_flats";
    public static final String QUERY_GET_FLATS = "select fl from Flat fl";

    public static final String GET_FLATS_BY_ID = "Flat.get_flats_by_id";
    public static final String QUERY_GET_FLATS_BY_ID = "select fl from Flat fl where fl.id = :id";

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

    @NotNull
    private int floor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="appuser_id")
    private AppUser appuser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="adress_id")
    private Address address;
}

/*INSERT INTO flat (id, size, price, rooms, floor, appuser_id, adress_id) VALUES (1, '42m', 250000.00, 2, 1, 3, 1);*/
/*DELETE FROM flat WHERE id=1;*/
