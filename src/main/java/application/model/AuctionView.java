package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = AuctionView.GET_ALL, query = AuctionView.QUERY_GET_ALL),
        @NamedQuery(name = AuctionView.GET_ALL_ASSETS_BY_TYPE, query = AuctionView.QUERY_GET_ALL_ASSETS_BY_TYPE),
        @NamedQuery(name = AuctionView.GET_ALL_ASSETS_BY_TYPE_AND_APPUSERROLE, query = AuctionView.QUERY_GET_ALL_ASSETS_BY_TYPE_AND_APPUSERROLE),
        @NamedQuery(name = AuctionView.GET_ALL_ASSETS_BY_TYPE_AND_ASSETID, query = AuctionView.QUERY_GET_ALL_ASSETS_BY_TYPE_AND_ASSETID),
        @NamedQuery(name = AuctionView.GET_ALL_ASSETS_BY_ADRESS, query = AuctionView.QUERY_GET_ALL_ASSETS_BY_ADRESS)
})

@Entity
@Table(name = "AUCTION_VIEW")
@Getter
@Setter
@NoArgsConstructor
public class AuctionView {

    public static final String GET_ALL = "AuctionView.get_all";
    public static final String QUERY_GET_ALL = "select av from AuctionView av";

    public static final String GET_ALL_ASSETS_BY_TYPE = "AuctionView.get_all_assets_by_type";
    public static final String QUERY_GET_ALL_ASSETS_BY_TYPE = "select av from AuctionView av where av.asset_type = :assetType";

    public static final String GET_ALL_ASSETS_BY_TYPE_AND_APPUSERROLE = "AuctionView.get_all_assets_by_type_and_appuserrole";
    public static final String QUERY_GET_ALL_ASSETS_BY_TYPE_AND_APPUSERROLE = "select av from AuctionView av where av.asset_type = :assetType and av.appuser_role = :appuserRole";

    public static final String GET_ALL_ASSETS_BY_TYPE_AND_ASSETID = "AuctionView.get_all_assets_by_type_and_assetid";
    public static final String QUERY_GET_ALL_ASSETS_BY_TYPE_AND_ASSETID = "select av from AuctionView av where av.asset_type = :assetType and av.asset_id = :assetId";

    public static final String GET_ALL_ASSETS_BY_ADRESS = "AuctionView.get_all_assets_by_adress";
    public static final String QUERY_GET_ALL_ASSETS_BY_ADRESS = "select av from AuctionView av where av.city = :city and av.postcode = :postcode and av.homenumber = :homenumber";

    //@Id
    //@Column(name = "id")
    //private Integer id;

    @Column(name = "appuser_id")
    private Integer appuser_id;

    @Column(name = "appuser_role")
    private Integer appuser_role;

    @Column(name = "asset_type")
    private String asset_type;

    @Id
    @Column(name = "asset_id")
    private Integer asset_id;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "street")
    private String street;

    @Column(name = "homenumber")
    private String homenumber;

    @Column(name = "localnumber")
    public String localnumber;

    @Column(name = "price")
    private Double price;

    @Column(name = "size")
    private String size;

    @Column(name = "rooms")
    private Integer rooms;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "plot_type")
    private String plot_type;

    @Column(name = "house_on_plot")
    private Integer house_on_plot;
}
