package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

