package NUT.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs at a specific time period.
 * An Event task has a description, start time, and end time.
 */

public class Events extends Task {
    private final String updatedName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    // Input formatter
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    // Display formatter
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a new Event task from user input.
     * @param rawInput The raw input string.
     * @throws NUTException If the format is invalid
     */

    public Events(String rawInput) throws NUTException {
        super(rawInput);

        int fromIndex = rawInput.indexOf("/from");
        int toIndex = rawInput.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            throw new NUTException("""
                        ____________________________________________________________
                        OOPS!!! Events must be in the format:
                        event <name> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>
                        ____________________________________________________________
                    """);
        }

        this.updatedName = rawInput.substring(0, fromIndex).trim();
        String startString = rawInput.substring(fromIndex + 5, toIndex).trim();
        String endString = rawInput.substring(toIndex + 3).trim();

        try {
            this.startTime = LocalDateTime.parse(startString, INPUT_FORMAT);
            this.endTime = LocalDateTime.parse(endString, INPUT_FORMAT);

            if (endTime.isBefore(startTime)) {
                throw new NUTException("""
                        ____________________________________________________________
                        OOPS!!! End time cannot be before start time!
                        ____________________________________________________________
                    """);
            }
        } catch (DateTimeParseException e) {
            throw new NUTException("""
                        ____________________________________________________________
                        OOPS!!! Invalid date/time format.
                        Please use dd/MM/yyyy HHmm
                        ____________________________________________________________
                    """);
        }
    }

    /**
     * Constructs an Event task from saved file data.
     * @param updatedName The task description
     * @param startString The start time string
     * @param endString The end time string
     * @param isDone Whether the task is completed
     */

    public Events(String updatedName, String startString, String endString, boolean isDone) {
        super(updatedName + " /from " + startString + " /to " + endString, isDone);
        this.updatedName = updatedName;
        this.startTime = LocalDateTime.parse(startString, INPUT_FORMAT);
        this.endTime = LocalDateTime.parse(endString, INPUT_FORMAT);
    }

    @Override
    public String getName() {
        return updatedName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[E] [x]" : "[E] [ ]");
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + updatedName
                + " | " + startTime.format(INPUT_FORMAT)
                + " | " + endTime.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + updatedName
                + " (from: " + startTime.format(DISPLAY_FORMAT)
                + " to: " + endTime.format(DISPLAY_FORMAT) + ")";
    }
}