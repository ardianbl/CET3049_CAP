package digipen.cet3049_cap.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super("Duplicate record detected: " + message);
    }
}
