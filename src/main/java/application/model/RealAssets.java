package application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "REAL_ASSETS")
@Getter
@Setter
@NoArgsConstructor
public class RealAssets {
    @Id
    @NotNull
    private int id;

    @NotNull
    private String type;
}
/*INSERT INTO real_assets (id, type) VALUES (1, 'flat');
INSERT INTO real_assets (id, type) VALUES (2, 'house');
INSERT INTO real_assets (id, type) VALUES (3, 'plot');*/