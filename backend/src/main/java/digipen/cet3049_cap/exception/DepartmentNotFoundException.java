package digipen.cet3049_cap.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() {
        super("No department record found.");
    }
}
