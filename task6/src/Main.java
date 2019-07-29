import entity.CheckPosition;
import service.CheckService;

/**
 * Created by User on 06.04.2019.
 */
public class Main {
    public static void main(String[] args) {
        CheckPosition[] checkPositions = new CheckPosition[4];

        checkPositions[0] = new CheckPosition("Milk",1.1,3);
        checkPositions[1] = new CheckPosition("Bread",2,2);
        checkPositions[2] = new CheckPosition("Cheese",10,1);
        checkPositions[3] = new CheckPosition("Meat",7,4);

        CheckService checkService = new CheckService();
        System.out.println(checkService.sumCheckPosition(checkPositions));
    }
}