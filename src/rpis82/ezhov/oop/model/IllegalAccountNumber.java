package rpis82.ezhov.oop.model;

public class IllegalAccountNumber extends RuntimeException {
    public IllegalAccountNumber (String message) {
        super(message);
    }

    public IllegalAccountNumber (String message, Throwable cause) {
        super(message, cause);
    }
}
