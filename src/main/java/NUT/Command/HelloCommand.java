package NUT.Command;

import NUT.Task.*;
import NUT.Ui.Ui;

public class HelloCommand implements Command {

    @Override
    public boolean execute(Ui ui) {
        ui.showHello();
        return false;
    }
}