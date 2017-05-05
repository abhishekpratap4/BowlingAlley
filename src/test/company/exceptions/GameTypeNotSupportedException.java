package test.company.exceptions;

/**
 * Created by pratap on 4/23/17.
 */
public class GameTypeNotSupportedException extends Throwable {
    public GameTypeNotSupportedException(String type) {
        System.out.println(type + "bowling game is not supported");
    }
}
