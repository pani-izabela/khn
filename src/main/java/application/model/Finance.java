package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FINANCE")
@Getter
@Setter
@NoArgsConstructor
public class Finance {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="appuser_id")
    private AppUser appuser;

    @NotNull
    private double amount;

    @NotNull
    private String currency;
}

/*INSERT INTO finance (id, amount, currency, appuser_id) VALUES (1, 2000000.00, 'PLN', 2);*/
/*INSERT INTO finance (id, amount, currency, appuser_id) VALUES (2, 10000000.00, 'PLN', 8);*/
/*INSERT INTO finance (id, amount, currency, appuser_id) VALUES (3, 1000000.00, 'PLN', 72);*/