package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PLOT")
@Getter
@Setter
@NoArgsConstructor
public class Plot {
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
    private AppUser appuser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="adress_id")
    private Adress adress;
}

/*INSERT INTO plot (id, size, price, type, appuser_id, adress_id) VALUES (1, '1000m', 60000.00, 'budowlana', 3, 2);*/
/*INSERT INTO plot (id, size, price, type, house_id, appuser_id, adress_id) VALUES (2, '2000m', 75000.00, 'budowlana',3, 77, 6);*/
