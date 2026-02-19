package nut.exception;

/**
 * Represents an exception specific to the Nut application.
 * <p>
 * This exception is thrown when user input is invalid or when an operation cannot be
 * completed due to application-specific constraints.
 * </p>
 */
public class NutException extends Exception {

    /**
     * Constructs a {@code NutException} with the specified detail message.
     *
     * @param msg The detail message explaining the cause of the exception.
     */
    public NutException(String msg) {
        super(msg);
    }
}
