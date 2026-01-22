package NUT.Command;

// This interface is to make sure every concrete command has an execute().
// This guides the program to either continue or terminate

public interface Command {
    // return true if program should exit
    // return false otherwise
    boolean execute();
}
