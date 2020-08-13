package application.bean;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Named
@RequestScoped
@Getter
@Setter
@ManagedBean
public class xxxBean {

    //wyswietlane w formularzu o id=creditCalculator
    private final double PERCENT= 3.5;
    private int numberOfLoanInstallment;
    private double amountOfCredit;
    private double monthlyIncome;
    private double monthlyExpenses;
    private Integer text = null;
    //pozostałę
    private double y;

    //wyswietlane w formularzu o id=results
    private boolean creditworthiness;
    private double possibleLoanAmount;
    private double installment;
    private double repaymentAmount;
    private String date;

    public void checkCreditworthiness2(){
        if(monthlyIncome>monthlyExpenses){
            possibleLoanAmount = amountOfCredit;
            calculateInstallment();
            calculateRepaymentAmount();
            calculateDateLoanTerm();
        }
        else{
            installment = 0;
            possibleLoanAmount = -1.0;
            repaymentAmount = 0.0;
            date = "0";
        }
    }

//metody prywatne
    private double getY(){
        return y = 1 + (PERCENT/100/12);
    }

    private double calculateInstallment(){
        double power = Math.pow(getY(), numberOfLoanInstallment);
        installment = Math.round(amountOfCredit * power * ((y-1)/(power-1)));
        return installment;
    }

    private double calculateRepaymentAmount(){
        repaymentAmount = installment * numberOfLoanInstallment;
        return repaymentAmount;
    }

    private String calculateDateLoanTerm(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, numberOfLoanInstallment);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        date = format1.format(calendar.getTime());
        return date;
    }

    private void resetFormCalculator(){
        numberOfLoanInstallment = 0;
        amountOfCredit = 0.0;
        monthlyIncome = 0.0;
        monthlyExpenses = 0.0;
    }
}
