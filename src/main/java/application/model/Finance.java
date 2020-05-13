package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Finance.GET_FINANCE_BY_APPUSERID, query = Finance.QUERY_GET_FINANCE_BY_APPUSERID),
        @NamedQuery(name = Finance.GET_FINANCE_BY_ID, query = Finance.QUERY_GET_FINANCE_BY_ID),
})

@Entity
@Table(name = "FINANCE")
@Getter
@Setter
@NoArgsConstructor
public class Finance {

    public static final String GET_FINANCE_BY_APPUSERID = "Finance.get_finance_by_appuserid";
    public static final String QUERY_GET_FINANCE_BY_APPUSERID = "select f from Finance f where f.appuser = :appuserid";

    public static final String GET_FINANCE_BY_ID = "Finance.get_finance_by_id";
    public static final String QUERY_GET_FINANCE_BY_ID = "select f from Finance f where f.id = :financeId";
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="appuser_id")
    //appUser lub app_user
    private AppUser appuser;

    @NotNull
    private double amount;

    @NotNull
    private String currency;
}

/*INSERT INTO finance (id, amount, currency, appuser_id) VALUES (1, 2000000.00, 'PLN', 2);*/
/*INSERT INTO finance (id, amount, currency, appuser_id) VALUES (2, 10000000.00, 'PLN', 8);*/
/*INSERT INTO finance (id, amount, currency, appuser_id) VALUES (3, 1000000.00, 'PLN', 72);*/