package service;

import myexception.ValueIsNotReadyException;

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
            throw new ValueIsNotReadyException("Illegal radius. Should be positive number.");
        }
    }
}
