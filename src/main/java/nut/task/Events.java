package nut.task;

import nut.exception.NutException;

/**
 * Represents an event task with a start and end time.
 * <p>
 * The input to construct an {@code Events} instance is expected to follow the format:
 * {@code <description> /from <start> /to <end>}.
 * For example: {@code project meeting /from Mon 2pm /to 4pm}.
 * </p>
 */
public class Events extends Task {
    // The event description without the {@code /from} and {@code /to} components.
    private final String updatedName;

    // The start time portion of the event, as provided by the user.
    private final String updatedStartTime;

    // The end time portion of the event, as provided by the user.
    private final String updatedEndTime;

    /**
     * Constructs an event task by parsing the provided input string.
     *
     * @param name The raw event input (excluding the {@code event} keyword), e.g.
     *             {@code "project meeting /from Mon 2pm /to 4pm"}.
     * @throws NutException If the input does not match the expected format
     *                      {@code <description> /from <start> /to <end>}.
     */
    public Events(String name) throws NutException {
        super(name);

        String[] parts = name.split("/");

        if (parts.length != 3
                || !parts[1].trim().startsWith("from")
                || !parts[2].trim().startsWith("to")) {
            throw new NutException("""
                        I couldn't crack that event format.
                        Use:
                        event <name> /from <start> /to <end>
                    """);
        }

        this.updatedName = parts[0].trim();
        this.updatedStartTime = parts[1].substring(parts[1].indexOf(" ") + 1).trim();
        this.updatedEndTime = parts[2].substring(parts[2].indexOf(" ") + 1).trim();
    }

    /**
     * Constructs an event task from stored fields.
     *
     * @param updatedName The event description.
     * @param updatedStartTime The event start time.
     * @param updatedEndTime The event end time.
     * @param isDone Whether the event is completed.
     */
    public Events(String updatedName, String updatedStartTime, String updatedEndTime, boolean isDone) {
        super(updatedName + " /from " + updatedStartTime + " /to " + updatedEndTime, isDone);
        this.updatedName = updatedName;
        this.updatedStartTime = updatedStartTime;
        this.updatedEndTime = updatedEndTime;
    }

    /**
     * Returns the event description (excluding the time components).
     *
     * @return The event description.
     */
    @Override
    public String getName() {
        return updatedName;
    }

    /**
     * Returns the status icon for this event task.
     *
     * @return {@code "[E] [x]"} if done, otherwise {@code "[E] [ ]"}.
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[E] [x]" : "[E] [ ]");
    }

    /**
     * Returns the string representation of this event task for display.
     *
     * @return A user-facing representation including status, description, and time range.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + updatedName
                + " (by: " + updatedStartTime
                + " to: " + updatedEndTime + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + updatedName
                + " | " + updatedStartTime + " | " + updatedEndTime;
    }
}
