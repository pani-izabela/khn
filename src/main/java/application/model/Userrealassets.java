/*package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERREALASSETS")
@Getter
@Setter
@NoArgsConstructor
public class Userrealassets {

    @Id
    @NotNull
    @Column(name="userrealassets_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "plot_id")
    private Plot plot;
}*/

/* Stworzyłam widok tym zapytaniem
CREATE VIEW userrealassets AS
SELECT ROW_NUMBER() OVER(ORDER BY Au.id) as id, Au.id as appuser_id, Fl.id as flat_id, Ho.id as house_id, Pl.id as plot_id
FROM APPUSER Au, FLAT Fl, HOUSE Ho, PLOT Pl

*/

/*
CREATE VIEW UserRealAssets AS
SELECT ROW_NUMBER() OVER(ORDER BY appuser_id) AS id, Au.id as appuser_id, Fl.id as flat_id, Ho.id as house_id, Pl.id as plot_id
FROM APPUSER Au, FLAT Fl, HOUSE Ho, PLOT Pl
WHERE Fl.flat_id = Fl.id
And Ho.house_id = Ho.id
And Pl.plot_id = Pl.id
And Au.appuser_id = Au.id
*/
