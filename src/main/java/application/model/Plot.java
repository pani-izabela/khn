package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Plot.GET_PLOTS, query = Plot.QUERY_GET_PLOTS),
        @NamedQuery(name = Plot.GET_PLOTS_BY_ID, query = Plot.QUERY_GET_PLOTS_BY_ID),
        @NamedQuery(name = Plot.GET_PLOT_BY_ADDRESS_ID, query = Plot.QUERY_GET_PLOT_BY_ADDRESS_ID)
})

@Entity
@Table(name = "PLOT")
@Getter
@Setter
@NoArgsConstructor
public class Plot {

    public static final String GET_PLOTS = "Plot.get_plots";
    public static final String QUERY_GET_PLOTS = "select pl from Plot pl";

    public static final String GET_PLOTS_BY_ID = "Plot.get_plots_by_id";
    public static final String QUERY_GET_PLOTS_BY_ID = "select pl from Plot pl where pl.id = :id";

    public static final String GET_PLOT_BY_ADDRESS_ID = "Plot.get_plot_by_address_id";
    public static final String QUERY_GET_PLOT_BY_ADDRESS_ID = "select pl from Plot pl where pl.address = :addressId";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    private String size;

    @NotNull
    private double price;

    @NotNull
    private String type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="house_id")
    private House house;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="appuser_id")
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="adress_id")
    private Address address;
}

/*INSERT INTO plot (id, size, price, type, appuser_id, adress_id) VALUES (1, '1000m', 60000.00, 'budowlana', 3, 2);*/
/*INSERT INTO plot (id, size, price, type, house_id, appuser_id, adress_id) VALUES (2, '2000m', 75000.00, 'budowlana',3, 77, 6);*/
