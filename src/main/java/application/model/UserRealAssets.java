package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = UserRealAssets.GET_USERREALASSETS, query = UserRealAssets.QUERY_GET_USERREALASSETS),
        @NamedQuery(name = UserRealAssets.GET_USERREALASSETS_BY_APPUSERID, query = UserRealAssets.QUERY_GET_USERREALASSETS_BY_APPUSERID),
        @NamedQuery(name = UserRealAssets.GET_USERREALASSETS_BY_HOUSE, query = UserRealAssets.QUERY_GET_USERREALASSETS_BY_HOUSE),
        @NamedQuery(name = UserRealAssets.GET_USERREALASSETS_BY_PLOT, query = UserRealAssets.QUERY_GET_USERREALASSETS_BY_PLOT),
        @NamedQuery(name = UserRealAssets.GET_USERREALASSETS_BY_FLAT, query = UserRealAssets.QUERY_GET_USERREALASSETS_BY_FLAT),
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

    public static final String GET_USERREALASSETS_BY_HOUSE = "Userrealassets.get_userrealassets_by_house";
    public static final String QUERY_GET_USERREALASSETS_BY_HOUSE = "select uas from UserRealAssets uas where uas.house = :houseId";

    public static final String GET_USERREALASSETS_BY_PLOT = "Userrealassets.get_userrealassets_by_plot";
    public static final String QUERY_GET_USERREALASSETS_BY_PLOT = "select uas from UserRealAssets uas where uas.plot = :plotId";

    public static final String GET_USERREALASSETS_BY_FLAT = "Userrealassets.get_userrealassets_by_flat";
    public static final String QUERY_GET_USERREALASSETS_BY_FLAT = "select uas from UserRealAssets uas where uas.flat = :flatId";

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
