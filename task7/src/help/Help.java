package help;

/**
 * Created by User on 14.04.2019.
 */
public class Help {
    private static final String strinfo = "It's help mode \nProgram output square and perimeter of circle or rectangle.\n " +
            "-с r\tr - circle radius \n" +
            "-r a b\ta, b - rectangle sides";
    private static final String strIsNullValue = "Введено неверное количество аргументов. \n"+
                                                 "Вызовите -h для более подробной информации.";
    public static void getStrInfo() {
        System.out.println(strinfo);
    }
    public static String getStrIsNullValue(){
        return strIsNullValue;
    }
}
