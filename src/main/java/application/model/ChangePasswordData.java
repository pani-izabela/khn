package application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordData {
    String email;
    String oldPass;
    String newPass;
}
