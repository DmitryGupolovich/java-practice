package service;

import myexception.ValueIsNotReadyException;
import myexception.ValueIsNullException;

/**
 * Created by User on 14.04.2019.
 */
public class Service {
    private double checkPositiveValue(double value) throws ValueIsNotReadyException {
        if ((value < 0) || (value == 0)) {
            throw new ValueIsNotReadyException("Число должно быть не отрицательным и больше нуля");
        }
        return value;
    }

    public double checkPositiveValue(String value) {
        try {
            return checkPositiveValue(Double.parseDouble(value));
        } catch (NumberFormatException ex) {
            throw new ValueIsNotReadyException("Вы ввели не число!");
        }
    }

    public void checkNullValue(String[] args) throws ValueIsNullException {
        if (args.length > 0) {
            switch (args[0]) {
                case "-r":
                    if (args.length != 3)
                        throw new ValueIsNullException("Отсутствует значение для ввода прямоугольника!");
                    break;
                case "-c":
                    if (args.length != 2)
                        throw new ValueIsNullException("Отсутствует значение для ввода!");
                    break;

            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Вы не ввели аргументы командной строки");
        }
    }
}
