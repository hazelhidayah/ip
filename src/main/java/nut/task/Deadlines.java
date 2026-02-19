package nut.task;

import nut.exception.NutException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 * <p>
 * A Deadline task contains a description and a date/time by which it must be completed.
 * It supports input formats for both date and time (dd/MM/yyyy HHmm), and date only (dd/MM/yyyy).
 * </p>
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

    // storage formatter
    private static final DateTimeFormatter STORAGE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a new Deadline task based on user input.
     * Parses the raw command string to extract the description and the deadline date.
     *
     * @param rawInput The raw command string (e.g., "return book /by 12/12/2024 1800").
     * @throws NutException If the format is invalid or the date cannot be parsed.
     */
    public Deadlines(String rawInput) throws NutException {
        super(rawInput);

        String[] parts = rawInput.split("/by");

        if (parts.length != 2 || // limit 2 ensures we only split into Description and Date, and the input is valid
                parts[0].trim().isEmpty()) { // invalid
            throw new NutException("""
                        I couldn't crack that deadline format.
                        Use:
                        deadline <name> /by <dd/MM/yyyy HHmm>
                        or
                        deadline <name> /by <dd/MM/yyyy>
                    """);
        }

        this.updatedName = parts[0].trim();
        String dateTimeString = parts[1].trim();

        parseDeadline(dateTimeString);
    }

    // Constructor for loading from a file
    public Deadlines(String updatedName, String dateTimeString, boolean isDone) throws NutException {
        super(updatedName + " /by " + dateTimeString);
        this.updatedName = updatedName;
        try {
            parseDeadline(dateTimeString);
        } catch (NutException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to parse the date string.
     * Tries to parse as DateTime first, falls back to Date only.
     *
     * @param dateString The string to parse.
     * @throws NutException If the date string matches neither format.
     */
    private void parseDeadline(String dateString) throws NutException {
        try { // try to parse as date + time
            this.deadline = LocalDateTime.parse(dateString, DATE_AND_TIME_INPUT);
            this.hasTime = true;
        } catch (DateTimeParseException e1) {
            try { // try parsing as Date only
                LocalDate dateOnly = LocalDate.parse(dateString, DATE_INPUT);
                this.deadline = dateOnly.atStartOfDay();
                this.hasTime = false; // hasTime = false for formatting
            } catch (DateTimeParseException e2) {
                throw new NutException("""
                        I couldn't crack that deadline format.
                        Use:
                        deadline <name> /by <dd/MM/yyyy HHmm>
                        or
                        deadline <name> /by <dd/MM/yyyy>
                    """);
            }
        }
    }

    @Override
    public String getName() {
        return updatedName;
    }

    public String getDeadline() {
        return hasTime
                ? deadline.format(DATE_AND_TIME_DISPLAY_FORMAT)
                : deadline.toLocalDate().format(DATE_DISPLAY_FORMAT);
    }

    public String toFileFormat() {
        String dateTimeStr = hasTime ?
                deadline.format(STORAGE_FORMAT) :
                deadline.toLocalDate().format(DATE_INPUT);
        return "D | " + (isDone ? "1" : "0") + " | " + updatedName + " | " + dateTimeStr;
    }

    // included [D]
    @Override
    public String getStatusIcon() {
        return (isDone ? "[D] [x]" : "[D] [ ]");
    }

    @Override
    public String toString() {
        String formattedDeadline = hasTime ?
                deadline.format(DATE_AND_TIME_DISPLAY_FORMAT) :
                deadline.toLocalDate().format(DATE_DISPLAY_FORMAT);
        return getStatusIcon() + " " + updatedName + " (by: " + formattedDeadline + ")";
    }
}
