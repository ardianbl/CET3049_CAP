package digipen.cet3049_cap.exception;

/**
 * Exception thrown when a department record cannot be located.
 */
public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() {
        super("No department record found.");
    }
}
