package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Userrealassets.GET_USERREALASSETS, query = Userrealassets.QUERY_GET_USERREALASSETS),
        @NamedQuery(name = Userrealassets.GET_USERREALASSETS_BY_APPUSERID, query = Userrealassets.QUERY_GET_USERREALASSETS_BY_APPUSERID)
})

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERREALASSETS")
public class Userrealassets {

    public static final String GET_USERREALASSETS = "Userrealassets.get_userrealassets";
    public static final String QUERY_GET_USERREALASSETS = "select uas from Userrealassets uas";

    public static final String GET_USERREALASSETS_BY_APPUSERID = "Userrealassets.get_userrealassets_by_appuserid";
    public static final String QUERY_GET_USERREALASSETS_BY_APPUSERID = "select uas from Userrealassets uas where uas.appUser = :appuserId";

    @Id
    @NotNull
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @ManyToOne
    @JoinColumn(name = "plot_id")
    private Plot plot;
}
