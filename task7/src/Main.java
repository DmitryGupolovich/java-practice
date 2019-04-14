import help.Help;
import model.Circle;
import model.Rectangle;
import myexception.ValueIsNotReadyException;
import service.Service;

/**
 * Created by User on 14.04.2019.
 */
public class Main {
    public static void main(String[] args) {
        Help help = new Help();
        help.getStrInfo();

        try {
            Service service = new Service();
            switch (args[0]) {
                case "-r":
                    Rectangle rectangle = new Rectangle(service,args[1],args[2]);
                    System.out.println(rectangle.toString());
                    break;
                case "-c":
                    Circle circle = new Circle(service,args[1]);
                    System.out.println(circle.toString());
                    break;
                case "-h":
                    System.out.println(help.getStrInfo());
                    break;
                default:
                    System.out.println("Unknown option " + args[0] + "\n Use -h to get help.");
            }
        } catch (ValueIsNotReadyException ex){
            System.out.println(ex.getMessage());
        }
    }
}
