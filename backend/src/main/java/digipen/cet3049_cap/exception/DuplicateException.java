package digipen.cet3049_cap.exception;

/**
 * Exception thrown when an operation would create a duplicate domain record.
 */
public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super("Error: " + message);
    }
}
