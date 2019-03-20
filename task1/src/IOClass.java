/**
 Напишите класс с методом main, в нём введите строку и выведите эту строку.
 Главное, чтобы ваш класс скомпилировался без ошибок, запустился и вывел введенные значения
 */
public class IOClass {
    private String value;
    public String getValue() {
        return value;
    }

    public void main(String value) {
        this.value = value;
    }

    public boolean CheckValue(String str){
        if (str.isEmpty() || str.equals(" ")) {
            return false;
        } else
            return true;
    }
}
