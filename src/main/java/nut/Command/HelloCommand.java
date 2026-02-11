package nut.Command;

import nut.Ui.Ui;

/**
 * Command that greets the user.
 * <p>
 * When executed, this command uses the {@link nut.Ui.Ui} to display a greeting message
 * and then returns control to the application (i.e., it does not terminate the program).
 * </p>
 */
public class HelloCommand implements Command {

    @Override
    public String execute(Ui ui) {
        return ui.showHello();
    }
}
