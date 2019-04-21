import help.Help;
import model.AbstractShape;
import model.Circle;
import model.Rectangle;
import myexception.ValueIsNotReadyException;
import myexception.ValueIsNullException;
import service.Service;

/**
 * Created by User on 14.04.2019.
 */
public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        try {
            service.checkNullValue(args);
        } catch (ValueIsNullException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        AbstractShape abstractShape = null;
        switch (args[0]) {
            case "-r":
                try {
                    abstractShape = new Rectangle(service.checkPositiveValue(args[1]), service.checkPositiveValue(args[2]));
                    break;
                } catch (ValueIsNotReadyException | ValueIsNullException ex) {
                    System.out.println(ex.getMessage());
                    System.exit(1);
                }
            case "-c":
                try {
                    abstractShape = new Circle(service.checkPositiveValue(args[1]));
                    break;
                } catch (ValueIsNotReadyException | ValueIsNullException ex) {
                    System.out.println(ex.getMessage());
                    System.exit(1);
                }
            case "-h":
                Help.getStrInfo();
                break;
            default:
                System.out.println("Unknown option " + args[0] + "\n Use -h to get help.");
        }
        if (abstractShape != null) {
            System.out.println(abstractShape.toString());
        }

    }
}
