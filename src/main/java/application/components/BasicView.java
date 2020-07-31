package application.components;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
@Getter
@Setter
public class BasicView {
    private String text;
    private int firstNumber;
    private int secondNumber;

    public int add(){
         return firstNumber + secondNumber;
    }

}
