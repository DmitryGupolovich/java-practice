package Service;

import Entity.CheckPosition;

/**
 * Created by User on 06.04.2019.
 */
public class CheckService {

    private double masRecursion(CheckPosition[] checkPositions, int index) {

        if (index < 0) {
            return 0;
        } else
            return (checkPositions[index].getCount() * checkPositions[index].getPrice()) + masRecursion(checkPositions, index - 1);
    }

    public double sumCheckposition(CheckPosition[] checkPositions) {

        return masRecursion(checkPositions, checkPositions.length - 1);

    }


}