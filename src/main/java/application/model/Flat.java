package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FLAT")
@Getter
@Setter
@NoArgsConstructor
public class Flat {

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
    private Adress adress;
}

/*INSERT INTO flat (id, size, price, rooms, floor, appuser_id, adress_id) VALUES (1, '42m', 250000.00, 2, 1, 3, 1);*/
/*DELETE FROM flat WHERE id=1;*/
