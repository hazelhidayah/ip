package nut.storage;

import nut.task.Events;
import nut.task.Deadlines;
import nut.task.ToDos;
import nut.task.TaskList;
import nut.task.Task;
import nut.exception.NutException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

        ArrayList<Task> tasks1 = getTasks(file, tasks); // retrieve the tasks from the file
        if (tasks1 != null) return tasks1; // if the file has tasks, return the tasks

        try (Scanner scanner = new Scanner(file)) {  // open the file for reading
            while (scanner.hasNextLine()) {  // read line by line
                String line = scanner.nextLine();
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
            String[] parts = line.split(" \\| ");  // Split by " | " → ["T", "0", "sleep"]
            if (parts.length < 3) return null;  // Invalid format, skip this line

            String type = parts[0];  // task type: "T", "D", or "E"
            boolean isDone = parts[1].equals("1");  // "1" = done, "0" = not done
            String description = parts[2];  // task description

            return switch (type) {
                case "T" ->  // ToDo task
                        new ToDos(description);
                case "D" -> {
                    if (parts.length < 4) yield null;
                    yield new Deadlines(description);  // have date
                }
                case "E" -> {
                    if (parts.length < 5) yield null;
                    yield new Events(description);  // have start and end dates
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

            FileWriter writer = new FileWriter(file);  // Open the file for writing
            for (int i = 0; i < tasks.size(); i++) {  // Loop through all tasks
                Task task = tasks.get(i);
                writer.write(task.toFileFormat() + "\n");  // Write a task in the file format
            }
            writer.close();  // Close the file
        } catch (IOException e) {
            throw new NutException("Nut couldn't stash tasks to: " + filePath);
        }
    }
}
