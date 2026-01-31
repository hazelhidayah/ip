package NUT.Command;

import NUT.Ui.Ui;

/*
 * Terminates when the user says 'bye'
 */

public class ByeCommand implements Command {

    @Override
    public boolean execute(Ui ui) {
        ui.showGoodbye();
        return true; // exit
    }

}
