package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = UserRealAssets.GET_USERREALASSETS, query = UserRealAssets.QUERY_GET_USERREALASSETS),
        @NamedQuery(name = UserRealAssets.GET_USERREALASSETS_BY_APPUSERID, query = UserRealAssets.QUERY_GET_USERREALASSETS_BY_APPUSERID)
})

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERREALASSETS")
public class UserRealAssets {

    public static final String GET_USERREALASSETS = "Userrealassets.get_userrealassets";
    public static final String QUERY_GET_USERREALASSETS = "select uas from UserRealAssets uas";

    public static final String GET_USERREALASSETS_BY_APPUSERID = "Userrealassets.get_userrealassets_by_appuserid";
    public static final String QUERY_GET_USERREALASSETS_BY_APPUSERID = "select uas from UserRealAssets uas where uas.appUser = :appuserId";

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
