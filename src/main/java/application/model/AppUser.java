package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = AppUser.GET_APPUSER_BY_ID, query = AppUser.QUERY_GET_APPUSER_BY_ID),
        @NamedQuery(name = AppUser.GET_APPUSERS, query = AppUser.QUERY_GET_APPUSERS),
        @NamedQuery(name = AppUser.GET_APPUSER_BY_EMAIL_AND_PASS, query = AppUser.QUERY_GET_APPUSER_BY_EMAIL_AND_PASS),
        @NamedQuery(name = AppUser.GET_APPUSER_BY_EMAIL, query = AppUser.QUERY_GET_APPUSER_BY_EMAIL)
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

    public static final String GET_APPUSER_BY_EMAIL_AND_PASS = "AppUser.get_appuser_by_name_and_email";
    public static final String QUERY_GET_APPUSER_BY_EMAIL_AND_PASS = "select au from AppUser au where au.email = :email and au.pass = :pass";

    public static final String GET_APPUSER_BY_EMAIL = "AppUser.get_appuser_by_email";
    public static final String QUERY_GET_APPUSER_BY_EMAIL = "select au from AppUser au where au.email = :email";

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
    joinColumns = @JoinColumn(name="appuser_id"),
    inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles = new ArrayList<>();
}
