package pba.devops.addressbook.ui.view.action.command.abstracts;

public abstract class AbstractCommand<ENTITY> implements ICommand<ENTITY> {

    final protected String commandName;

    public AbstractCommand(String commandName) {
        this.commandName = commandName;

    }
}
