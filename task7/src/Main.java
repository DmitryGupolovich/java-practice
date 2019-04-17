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
        Help help = new Help();
        help.getStrInfo();

        try {
            Service service = new Service(); //Я подумал раз вызывать проверку в конструкторе, то зачем дважды создавать объект
            service.checkNullvalue(args);

            AbstractShape abstractShape = null;

            switch (args[0]) {
                case "-r":
                    abstractShape = new Rectangle(service, args[1], args[2]);
                    break;
                case "-c":
                    abstractShape = new Circle(service,args[1]);
                    break;
                case "-h":
                    System.out.println(help.getStrInfo());
                    break;
                default:
                    System.out.println("Unknown option " + args[0] + "\n Use -h to get help.");
            }
             if (abstractShape != null) {
                 System.out.println(abstractShape.toString());
            }
        } catch (ValueIsNotReadyException ex){
            System.out.println(ex.getMessage());
        } catch (ValueIsNullException ex){
            System.out.println(ex.getMessage());
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
        }
    }
}
