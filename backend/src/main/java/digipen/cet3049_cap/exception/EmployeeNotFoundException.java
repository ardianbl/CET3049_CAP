package digipen.cet3049_cap.exception;

/**
 * Exception thrown when an employee record is not found in the data store.
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("No employee record found.");
    }
}
