package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = AuctionView.GET_ALL, query = AuctionView.QUERY_GET_ALL)
})

@Entity
@Table(name = "AUCTION_VIEW")
@Getter
@Setter
@NoArgsConstructor
public class AuctionView {

    public static final String GET_ALL = "AuctionView.get_all";
    public static final String QUERY_GET_ALL = "select av from AuctionView av";

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "appuser_id")
    private Integer appuser_id;

    @Column(name = "asset_type")
    private String asset_type;

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
