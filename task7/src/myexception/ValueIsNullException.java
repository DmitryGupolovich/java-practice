package myexception;

/**
 * Created by User on 16.04.2019.
 */
public class ValueIsNullException extends IndexOutOfBoundsException {

    public ValueIsNullException(String s) {
        super(s);
    }
}
