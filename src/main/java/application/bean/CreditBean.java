package application.bean;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@RequestScoped
@Getter
@Setter
@ManagedBean
public class CreditBean {

    private int numberOfLoanInstallment;
    private double amountOfCredit;
    private double monthlyIncome;
    private double monthlyExpenses;
}
