package NUT.Task;

import NUT.NUT;

/**
 * Exception thrown when a specific application-defined error occurs.
 * This indicates that an error occurred, and usually
 * requires the caller to re-enter an input.
 */
public class NUTException extends Exception {

    /**
     * Constructs a new exception with a specific detail message.
     * @param msg the detail message
     */
    public NUTException(String msg) {
        super(msg);
    }
}
