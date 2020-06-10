package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@NamedQueries({
        @NamedQuery(name = Address.GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO, query = Address.QUERY_GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO),
        @NamedQuery(name = Address.GET_ADDRESS_BY_ID, query = Address.QUERY_GET_ADDRESS_BY_ID),
        @NamedQuery(name = Address.GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO_AND_TYPE, query = Address.QUERY_GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO_AND_TYPE)
})

@Entity
@Table(name = "ADRESS")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    public static final String GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO = "Address.get_address_by_city_and_street_and_house_no";
    public static final String QUERY_GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO = "select ad from Address ad where ad.city = :city and ad.street = :street and ad.homeNumber = :houseNo";

    public static final String GET_ADDRESS_BY_ID = "Address.get_address_by_id";
    public static final String QUERY_GET_ADDRESS_BY_ID = "select ad from Address ad where ad.id = :id";

    public static final String GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO_AND_TYPE = "Address.get_address_by_city_and_street_and_house_no_and_type";
    public static final String QUERY_GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO_AND_TYPE = "select ad from Address ad where ad.city = :city and ad.street = :street and ad.homeNumber = :houseNo and ad.realAssetsId = :assetId";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String homeNumber;

    private String localNumber;

    @NotNull
    private String postcode;

    @NotNull
    private int realAssetsId;
}

/*INSERT INTO adress (id, city, street, homeNumber, localNumber, postcode, realassetsid) VALUES (1, 'Warszawa', 'Różana', '10a', '15', '01-657', 1);*/
/*INSERT INTO adress (id, city, street, homeNumber, localNumber, postcode, realassetsid) VALUES (2, 'Radom', 'Błękitna', '112', '10', '50-700', 3);*/
/*INSERT INTO adress (id, city, street, homeNumber, localNumber, postcode, realassetsid) VALUES (3, 'Pułtusk', 'Mickiewicza', '80', '', '06-100', 2);*/
/*INSERT INTO adress (id, city, street, homeNumber, localNumber, postcode, realassetsid) VALUES (4, 'Dukla', 'Podkarpacka', '5', '12v', '40-400', 2);*/
/*INSERT INTO adress (id, city, street, homeNumber, localNumber, postcode, realassetsid) VALUES (5, 'Tomaszów Mazowiecki', 'Słowackiego', '12', '', '12-123', 2);*/
