/*
package application.bean;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.pow;

@Named
@RequestScoped
@Getter
@Setter
@ManagedBean
public class CreditBean {

    private final double PERCENT= 3.5;
    private String numberOfLoanInstallment = "0";
    private String amountOfCredit = "0,0";
    private String monthlyIncome = "0.0";
    private String monthlyExpenses = "0.0";
    private double y;

    */
/*private int creditworthiness;
    private double installment;
    private double repaymentAmount;

    public int checkCreditworthiness(){
        double monthlyIncomeDou = Double.parseDouble(monthlyIncome);
        double monthlyExpensesDou = Double.parseDouble(monthlyIncome);

        if(monthlyIncomeDou>monthlyExpensesDou){
            creditworthiness = 1;
        }
        else if(monthlyIncomeDou<=monthlyExpensesDou && monthlyIncomeDou>0.0 && monthlyExpensesDou>0.0){
            creditworthiness = 0;
        }
        else if(monthlyIncomeDou==0.0 || monthlyExpensesDou==0.0){
            creditworthiness = 2;
        }
        return creditworthiness;
    }*//*


    */
/*public double calculateInstallment(){
        double power = Math.pow(getY(), numberOfLoanInstallment);
        installment = Math.round(amountOfCredit * power * ((y-1)/(power-1)));
        return installment;
    }

    public double calculateRepaymentAmount(){
        repaymentAmount = installment * numberOfLoanInstallment;
        return repaymentAmount;
    }

    public String calculateDateLoanTerm(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, numberOfLoanInstallment);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        return format1.format(calendar.getTime());
    }*//*


//metody prywatne
    private double getY(){
        return y = 1 + (PERCENT/100/12);
    }

}
*/
