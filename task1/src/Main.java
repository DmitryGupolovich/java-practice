import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        IOClass ioClass = new IOClass();

        System.out.println("Enter the string...");

        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();
        if (!ioClass.CheckValue(value)) {
            System.out.println("Invalid input string");
        } else {
            ioClass.main(value);
            System.out.println("Your string is "+ioClass.getValue());
        }
    }
}
