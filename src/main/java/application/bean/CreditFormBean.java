package application.bean;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class CreditFormBean {

    private String name = "Iza";
    private String surname = "Łach";
}
