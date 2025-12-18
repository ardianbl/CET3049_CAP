package digipen.cet3049_cap.exception;

/**
 * Exception thrown when a provided promotion date is invalid (e.g. before today).
 */
public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
        super("Invalid date. Cannot promote employee before today.");
    }
}
