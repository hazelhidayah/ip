package nut.storage;

import nut.task.Events;
import nut.task.Deadlines;
import nut.task.ToDos;
import nut.task.TaskList;
import nut.task.Task;
import nut.exception.NutException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Handles the persistence of tasks to and from local storage.
 * <p>
 * The {@code storage} component is responsible for reading saved tasks from a data file at startup
 * and writing the current task list back to disk after changes, so the user's tasks persist between
 * runs of the application.
 * </p>
 */
public class Storage {
    private final String filePath;  // Path to the data file (e.g., "Nut.txt")

    /**
     * Constructs a storage object with the specified file path.
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * @return ArrayList of tasks loaded from the file.
     * @throws NutException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws NutException {
        ArrayList<Task> tasks = new ArrayList<>();  // create an empty list to store tasks
        File file = new File(filePath);  // referencing the file

        ArrayList<Task> existingTasks = getTasks(file, tasks); // retrieve the tasks from the file
        if (existingTasks != null) {
            return existingTasks; // if the file has tasks, return the tasks
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {  // read line by line
                Task task = parseTaskFromFile(line);  // convert the line to a task object
                if (task != null) {  // add if parsing succeeded
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new NutException("Nut hit a shell while loading tasks from: " + filePath);
        }
        return tasks;  // return list of tasks
    }

    // Handles the case if the file does not exist.
    private static ArrayList<Task> getTasks(File file, ArrayList<Task> tasks) {
        if (!file.exists()) { // if the file doesn't exist
            return tasks;
        }
        return null;
    }

    /**
     * Parses a line from the file into a task object.
     *
     * @param line The line from the file to parse.
     * @return The parsed task object, or null if parsing fails.
     */
    private Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ", 3); // Preserve task fields that contain delimiters.
            if (parts.length < 3) return null;  // Invalid format, skip this line

            String type = parts[0];  // task type: "T", "D", or "E"
            if (!parts[1].equals("0") && !parts[1].equals("1")) {
                return null;
            }
            boolean isDone = parts[1].equals("1");  // "1" = done, "0" = not done
            String payload = parts[2];

            return switch (type) {
                case "T" ->  // ToDo task
                        new ToDos(payload, isDone);
                case "D" -> {
                    int dateSeparator = payload.lastIndexOf(" | ");
                    if (dateSeparator < 0) {
                        yield null;
                    }
                    String description = payload.substring(0, dateSeparator);
                    String date = payload.substring(dateSeparator + 3);
                    yield new Deadlines(description, date, isDone); // have date
                }
                case "E" -> {
                    int endSeparator = payload.lastIndexOf(" | ");
                    if (endSeparator < 0) {
                        yield null;
                    }
                    int startSeparator = payload.lastIndexOf(" | ", endSeparator - 1);
                    if (startSeparator < 0) {
                        yield null;
                    }
                    String description = payload.substring(0, startSeparator);
                    String start = payload.substring(startSeparator + 3, endSeparator);
                    String end = payload.substring(endSeparator + 3);
                    yield new Events(description, start, end, isDone); // have start and end dates
                }
                default -> null;
            };
        } catch (NutException e) {
            return null;  // if task creation fails, skip
        }
    }

    /**
     * Saves all tasks to the file.
     *
     * @param tasks The TaskList containing all tasks to save.
     * @throws NutException If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws NutException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();  // Get a parent directory
            if (parentDir != null) {
                Files.createDirectories(parentDir.toPath());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
                for (int i = 0; i < tasks.size(); i++) {  // Loop through all tasks
                    Task task = tasks.get(i);
                    writer.write(task.toFileFormat() + "\n");  // Write a task in the file format
                }
            }
        } catch (IOException e) {
            throw new NutException("Nut couldn't stash tasks to: " + filePath);
        }
    }
}
