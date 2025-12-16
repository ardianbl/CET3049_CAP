package digipen.cet3049_cap.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("No employee record found.");
    }
}
