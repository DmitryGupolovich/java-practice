package service;

import myexception.ValueIsNotReadyException;
import myexception.ValueIsNullException;

/**
 * Created by User on 14.04.2019.
 */
public class Service {
    public double checkPositiveValue(double value) throws ValueIsNotReadyException {
        if ((value < 0) || (value == 0)) {
            throw new ValueIsNotReadyException("Число должно быть не отрицательным и больше нуля");
        } else {
            return value;
        }
    }
    public double checkPositiveValue(String value) {
        try  {
           return checkPositiveValue(Double.parseDouble(value));

        } catch (NumberFormatException ex){
            throw new ValueIsNotReadyException("Вы ввели не число!");
        }
    }
    public void checkNullvalue(String[] args) throws ValueIsNullException {

            if (args[0]!=null){
                switch (args[0]){
                    case "-r":
                        if (args.length==1 || args.length==2)
                            throw new ValueIsNullException("Отсутствует значение для ввода прямоугольника!");
                            break;
                    case "-c":
                        if (args[1]==null)
                            throw new ValueIsNullException("Отсутствует значение для ввода!");
                        break;

                }
            }
    }
}
