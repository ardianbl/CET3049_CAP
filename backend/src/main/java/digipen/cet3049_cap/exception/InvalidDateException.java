package digipen.cet3049_cap.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
        super("Invalid date. Cannot promote employee before today.");
    }
}
