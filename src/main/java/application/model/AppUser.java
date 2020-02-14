package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private String pass;
}
