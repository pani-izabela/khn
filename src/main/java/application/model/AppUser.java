package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = AppUser.GET_APPUSER_BY_ID, query = AppUser.QUERY_GET_APPUSER_BY_ID),
        @NamedQuery(name = AppUser.GET_APPUSERS, query = AppUser.QUERY_GET_APPUSERS)
})

@Entity
@Table(name = "APPUSER")
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    public static final String GET_APPUSER_BY_ID = "AppUser.get_appuser_by_id";
    public static final String QUERY_GET_APPUSER_BY_ID = "select au from AppUser au where au.id = :id";

    public static final String GET_APPUSERS = "AppUser.get_appusers";
    public static final String QUERY_GET_APPUSERS = "select au from AppUser au";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String pass;
}
