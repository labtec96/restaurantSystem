package error;

/**
 * Created by ch on 2020-05-15
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(final String message) {
        super(message);
    }
}
