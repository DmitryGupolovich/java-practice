package myexception;

/**
 * Created by User on 14.04.2019.
 */
public class ValueIsNotReadyException extends IllegalArgumentException {

    public ValueIsNotReadyException(String s) {
        super(s);
    }
}



