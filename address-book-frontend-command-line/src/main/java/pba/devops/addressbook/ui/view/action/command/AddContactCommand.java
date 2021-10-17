package pba.devops.addressbook.ui.view.action.command;

import pba.devops.addressbook.dataresolver.ContactRemote;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.ui.useraction.UserAction;
import pba.devops.addressbook.ui.view.action.command.abstracts.AbstractCommand;
import pba.devops.addressbook.ui.view.action.command.session.AddContactSession;
import pba.devops.addressbook.ui.view.action.command.session.abstracts.ISession;
import pba.devops.addressbook.ui.view.action.command.session.abstracts.IStep;
import pba.devops.addressbook.utils.BeanProviderUtils;

public class AddContactCommand extends AbstractCommand<Contact> {

    public AddContactCommand(String commandName) {
        super(commandName);
    }

    @Override
    public Boolean isMyJob(String userCommand) {
        return
                commandName.equalsIgnoreCase(userCommand.trim());
    }

    @Override
    public JobDone<Contact> doMyJob(String userCommand) {

        if (!isMyJob(userCommand)) {
            // Nothing to do --
            return null;
        }
        ISession<Contact> addContactSession = new AddContactSession();

        addContactSession.steps().forEach(step -> stepSetting(step));

        Contact contact = addContactSession.entity();
        if (contact != null) {
            BeanProviderUtils.getBean(ContactRemote.class).add(contact);
            return
                new JobDone<Contact>(contact);
        }
        // Process has been cancelled --
        return null;
    }

    /*
     * Private
     */

    private void stepSetting(IStep step) {
        Object value;
        do {
            value = new UserAction().userAnswer(step.fieldInformation());
        } while (step.value(value).isInError());
        step.subSteps()
            .filter(subStep -> step.value().toString().equalsIgnoreCase(subStep.stepParent()))
            .forEach(subStep -> stepSetting(subStep));
    }
}
