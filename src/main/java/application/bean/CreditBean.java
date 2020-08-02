package application.bean;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import static java.lang.Math.pow;

@Named
@RequestScoped
@Getter
@Setter
@ManagedBean
public class CreditBean {

    private final double PERCENT= 3.5;
    private int numberOfLoanInstallment;
    private double amountOfCredit;
    private double monthlyIncome;
    private double monthlyExpenses;
    private double y;

    private int creditworthiness;
    private double installment;
    private double repaymentAmount;

    public int checkCreditworthiness(){
        if(monthlyIncome>monthlyExpenses){
            creditworthiness = 1;
        }
        else if(monthlyIncome<=monthlyExpenses && monthlyIncome>0.0 && monthlyExpenses>0.0){
            creditworthiness = 0;
        }
        else if(monthlyIncome==0.0 || monthlyExpenses==0.0){
            creditworthiness = 2;
        }
        return creditworthiness;
    }

    public double calculateInstallment(){
        double power = pow(numberOfLoanInstallment, getY());
        installment = amountOfCredit * power * ((y-1)/(power-1));
        return installment;
    }

    public double calculateRepaymentAmount(){
        return repaymentAmount = installment * numberOfLoanInstallment;
    }

//metody prywatne
    private double getY(){
        return y = 1 + (PERCENT/12);
    }

}
