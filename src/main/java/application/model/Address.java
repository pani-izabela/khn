package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADRESS")
@Getter
@Setter
@NoArgsConstructor
public class Address {
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
