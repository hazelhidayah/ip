package NUT.Command;

/*
 * Terminates when the user says 'bye'
 */

public class ByeCommand implements Command {

    @Override
    public boolean execute() {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Orite, bai bai \\(^-^)/"); // we use 2 '\' to print out one \
        System.out.println("    ____________________________________________________________\n");
        return true; // exit
    }

}
