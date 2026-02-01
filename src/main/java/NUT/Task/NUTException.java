package NUT.Task;

import NUT.NUT;

/**
 * Signals an application-specific, user-correctable error.
 *
 * <p>{@code NUTException} is thrown when an operation cannot be completed due to invalid or
 * incomplete user input (for example, an unrecognised command or a missing required argument).
 * It is intended to be caught at the UI or application boundary so an appropriate message can be
 * shown and the user can try again.</p>
 *
 * <p>This exception carries a human-readable detail message describing what went wrong.</p>
 */
public class NUTException extends Exception {

    /**
     * Constructs a new exception with a specific detail message.
     *
     * @param msg the detail message
     */
    public NUTException(String msg) {
        super(msg);
    }
}
