package NUT.Task;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> list;
    private static final String FILE_PATH = "./data/NUT.txt";

    // constructor
    public TaskList() {
        this.list = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Adds a new task to the list and automatically saves to file.
     * @param task The task to be added
     */

    public void add(Task task) {
        list.add(task);
        System.out.println("____________________________________________________________\n");
        System.out.println("added: " + task + "\n");
        System.out.println("____________________________________________________________\n");
        saveToFile();
    }

    /**
     * Gets a task at the specified index.
     * @param index The index of the task
     * @return The task at the specified index
     */

    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return Size of the task list
     */

    public int size() {
        return list.size();
    }

    /**
     * Deletes a task at the specified index and automatically saves to file.
     * @param index The index of the task to delete
     */

    public void delete(int index) {
        if (index >= 0 && index < list.size()) {
            Task removed = list.remove(index);
            System.out.println("Okie, I've removed this task:");
            System.out.println("  " + removed);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            saveToFile();
        }
    }

    /**
     * Marks a task as done and automatically saves to file.
     * @param index The index of the task to mark
     */

    public void markTask(int index) {
        if (index >= 0 && index < list.size()) {
            list.get(index).mark();
            saveToFile();
        }
    }

    /**
     * Unmarks a task and automatically saves to file.
     * @param index The index of the task to unmark
     */

    public void unmarkTask(int index) {
        if (index >= 0 && index < list.size()) {
            list.get(index).unmark();
            saveToFile();
        }
    }

    /**
     * Lists all tasks in the task list.
     */

    public void listAll() {
        if (list.isEmpty()) {
            System.out.println("No tasks in your list yet!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
    }

    /**
     * Saves all tasks to the hard disk automatically.
     * Creates the data directory if it doesn't exist.
     */

    private void saveToFile() {
        try {
            File file = new File(FILE_PATH);

            File parentDir = file.getParentFile();

            // create data directory if it doesn't exist
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // write to file
            FileWriter writer = new FileWriter(file);
            for (Task task : list) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the hard disk when the program starts up.
     * If the file doesn't exist, starts with an empty task list.
     */

    private void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No previous data found º~º");
                return;
            }

            Scanner scanner = new Scanner(file);
            int count = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    list.add(task);
                    count++;
                }
            }
            scanner.close();

            if (count > 0) {
                System.out.println("Loaded " + count + " task(s) from file.");
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file into a Task object.
     * Format expected:
     * - T | 0/1 | description
     * - D | 0/1 | description | deadline
     * - E | 0/1 | description | start | end
     * @param line The line from the file
     * @return A Task object, or null if parsing fails
     */

    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null;
            }

            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            Task task = null;

            if (type.equals("T")) {
                task = new ToDos(description, isDone);
            } else if (type.equals("D")) {
                if (parts.length < 4) {
                    return null;
                }
                String deadline = parts[3].trim();
                task = new Deadlines(description, deadline, isDone);
            } else if (type.equals("E")) {
                if (parts.length < 5) {
                    return null;
                }
                String startTime = parts[3].trim();
                String endTime = parts[4].trim();
                task = new Events(description, startTime, endTime, isDone);
            }

            return task;
        } catch (Exception e) {
            System.out.println("Error parsing task: " + line);
            return null;
        }
    }
}