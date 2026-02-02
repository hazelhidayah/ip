package nut.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 * A {@code Deadlines} task contains a description and a date/time by which it must be completed.
 * It supports input formats for both date and time ({@code dd/MM/yyyy HHmm}), and date only
 * ({@code dd/MM/yyyy}).
 */
public class Deadlines extends Task {
    // The description of the task without the /by command.
    private final String updatedName;

    // The parsed deadline date and time.
    private LocalDateTime deadline;

    // Indicates if the user provided a specific time or just a date.
    private boolean hasTime;

    // Input formatters
    private static final DateTimeFormatter DATE_AND_TIME_INPUT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATE_INPUT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Output formatters
    private static final DateTimeFormatter DATE_AND_TIME_DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter DATE_DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Storage formatter
    private static final DateTimeFormatter STORAGE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a new deadline task based on user input.
     * Parses the raw command string to extract the description and the deadline date/time.
     *
     * @param rawInput raw command content after the {@code deadline} keyword
     *                (e.g., {@code "return book /by 12/12/2024 1800"})
     * @throws NutException if the format is invalid or the date/time cannot be parsed
     */
    public Deadlines(String rawInput) throws NutException {
        super(rawInput);

        String[] parts = rawInput.split("/by");

        // limit: 2 ensures we only split into Description and Date, and the input is valid
        if (parts.length != 2 || parts[0].trim().isEmpty()) {
            throw new NutException("""
                    ____________________________________________________________
                    OOPS!!! Deadlines must be in the format:
                    deadline <name> /by <dd/MM/yyyy HHmm>
                    or
                    deadline <name> /by <dd/MM/yyyy>
                    ____________________________________________________________
                    """);
        }

        this.updatedName = parts[0].trim();
        String dateTimeString = parts[1].trim();

        parseDeadline(dateTimeString);
    }

    /**
     * Constructs a deadline task from stored data.
     *
     * @param updatedName    task description (without {@code /by})
     * @param dateTimeString deadline date/time string in a supported input format
     * @param isDone         whether the task is completed
     * @throws NutException if {@code dateTimeString} cannot be parsed
     */
    public Deadlines(String updatedName, String dateTimeString, boolean isDone) throws NutException {
        super(updatedName + " /by " + dateTimeString);
        this.updatedName = updatedName;
        this.isDone = isDone;

        parseDeadline(dateTimeString);
    }

    /**
     * Parses the deadline string.
     * Tries to parse as date+time first, and falls back to date-only.
     *
     * @param dateTimeString the string to parse
     * @throws NutException if the string matches neither supported format
     */
    private void parseDeadline(String dateTimeString) throws NutException {
        try {
            this.deadline = LocalDateTime.parse(dateTimeString, DATE_AND_TIME_INPUT);
            this.hasTime = true;
        } catch (DateTimeParseException e1) {
            try {
                LocalDate dateOnly = LocalDate.parse(dateTimeString, DATE_INPUT);
                this.deadline = dateOnly.atStartOfDay();
                this.hasTime = false;
            } catch (DateTimeParseException e2) {
                throw new NutException("""
                        ____________________________________________________________
                        OOPS!!! Deadlines must be in the format:
                        deadline <name> /by <dd/MM/yyyy HHmm>
                        or
                        deadline <name> /by <dd/MM/yyyy>
                        ____________________________________________________________
                        """);
            }
        }
    }

    @Override
    public String getName() {
        return updatedName;
    }

    /**
     * Returns the formatted deadline date/time for display.
     *
     * @return the deadline in a user-friendly format
     */
    public String getDeadline() {
        return hasTime
                ? deadline.format(DATE_AND_TIME_DISPLAY_FORMAT)
                : deadline.toLocalDate().format(DATE_DISPLAY_FORMAT);
    }

    /**
     * Returns the string representation used for saving this task to storage.
     *
     * @return the file format string for this deadline task
     */
    @Override
    public String toFileFormat() {
        String dateTimeStr = hasTime
                ? deadline.format(STORAGE_FORMAT)
                : deadline.toLocalDate().format(DATE_INPUT);
        return "D | " + (isDone ? "1" : "0") + " | " + updatedName + " | " + dateTimeStr;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[D] [x]" : "[D] [ ]");
    }

    @Override
    public String toString() {
        String formattedDeadline = hasTime
                ? deadline.format(DATE_AND_TIME_DISPLAY_FORMAT)
                : deadline.toLocalDate().format(DATE_DISPLAY_FORMAT);
        return getStatusIcon() + " " + updatedName + " (by: " + formattedDeadline + ")";
    }
}