package service;

import entity.CheckPosition;

/**
 * Created by User on 06.04.2019.
 */
public class CheckService {

    private double getSum(CheckPosition[] checkPositions, int index) {
        if (index < 0) {
            return 0;
        }
        return (checkPositions[index].getCount() * checkPositions[index].getPrice()) + getSum(checkPositions, index - 1);
    }

    public double sumCheckPosition(CheckPosition[] checkPositions) {

        return getSum(checkPositions, checkPositions.length - 1);
    }


}