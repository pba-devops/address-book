package pba.devops.addressbook.ui.view.action;

import org.springframework.util.StringUtils;
import pba.devops.addressbook.dataresolver.ContactRemote;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.ui.view.ContactsView;
import pba.devops.addressbook.ui.view.NewContactView;
import pba.devops.addressbook.ui.view.WelcomeView;
import pba.devops.addressbook.ui.view.action.command.AddContactCommand;
import pba.devops.addressbook.ui.view.action.command.DeleteContactCommand;
import pba.devops.addressbook.ui.view.action.command.UpdateContactCommand;
import pba.devops.addressbook.ui.view.action.command.abstracts.ICommand;
import pba.devops.addressbook.utils.BeanProviderUtils;
import pba.devops.addressbook.utils.CommandUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Action {

    VIEW_OF_MENU("menu") {
        @Override
        public void instruction() {
            System.out.format("[ %s ] - Back to the main menu -\n", command);
        }
        @Override
        public void run(String userAnswer) {
            if(isMyJob(userAnswer)) {
                BeanProviderUtils.getBean(WelcomeView.class).load();
            }
        }
    },
    VIEW_OF_CONTACTS("contacts") {
        @Override
        public void instruction() {
            System.out.format("[ %s ] - Show your contacts -\n", command);
        }
        @Override
        public void run(String userAnswer) {
            if(isMyJob(userAnswer)) {
                BeanProviderUtils.getBean(ContactsView.class).load();
            }
        }
    },
    VIEW_NEW_CONTACT("add") {

        private ICommand<Contact> addContactCommand = new AddContactCommand(command());

        @Override
        public void instruction() {
            System.out.format("[ %s ] - Add a new contact -\n", command);
        }

        @Override
        public Boolean isMyJob(String userCommand) {
            return
                addContactCommand.isMyJob(userCommand);
        }

        @Override
        public void run(String userAnswer) {

            if(!isMyJob(userAnswer)) {
                return;
            }
            ICommand.IJobDone jobDone = addContactCommand.doMyJob(userAnswer);
            if(jobDone.isInError()) {
                BeanProviderUtils.getBean(ContactsView.class).load(jobDone.message());
            } else {
                BeanProviderUtils.getBean(ContactsView.class).load();
            }
        }
    },
    VIEW_UPDATE_CONTACT("update") {

        private ICommand<Contact> updateContactCommand = new UpdateContactCommand(command());

        @Override
        public void instruction() {
            System.out.format("[ %s {contact-id} {property} {new-value} ] - Update a contact property -\n", command);
        }
        @Override
        public Boolean isMyJob(String userCommand) {
            return
                updateContactCommand.isMyJob(userCommand);
        }
        @Override
        public void run(String userAnswer) {

            if(!isMyJob(userAnswer)) {
                return;
            }
            ICommand.IJobDone jobDone = updateContactCommand.doMyJob(userAnswer);
            if(jobDone.isInError()) {
                BeanProviderUtils.getBean(ContactsView.class).load(jobDone.message());
            } else {
                BeanProviderUtils.getBean(ContactsView.class).load();
            }

        }
    },
    DELETE_CONTACT("delete") {

        private ICommand<Contact> deleteContactCommand = new DeleteContactCommand(command());

        @Override
        public void instruction() {
            System.out.format("[ %s {contact-id} ] - Delete contact -\n", command);
        }
        @Override
        public Boolean isMyJob(String userCommand) {
            return deleteContactCommand.isMyJob(userCommand);
        }
        @Override
        public void run(String userAnswer) {

            if(!isMyJob(userAnswer)) {
                return;
            }
            ICommand.IJobDone jobDone = deleteContactCommand.doMyJob(userAnswer);
            if(jobDone.isInError()) {
                BeanProviderUtils.getBean(ContactsView.class).load(jobDone.message());
            } else {
                BeanProviderUtils.getBean(ContactsView.class).load();
            }
        }
    },
    VIEW_QUIT("quit") {
        @Override
        public void instruction() {
            System.out.format("[ %s ] - Leave the app -\n", command);
        }
        @Override
        public void run(String userAnswer) {
            if(isMyJob(userAnswer)) {
                System.out.println("Good bye");
            }
        }
    };

    final protected String command;

    private Action(String command) {
        this.command = command;
    }

    protected String command() {
        return command;
    }

    abstract public void instruction();
    abstract public void run(String userCommand);

    public Boolean isMyJob(String userCommand) {
        return Action.isMyJob(command, userCommand);
    }

    static private Boolean isMyJob(String actionCommand, String userCommand) {
        return
            StringUtils.isEmpty(userCommand)
                ? Boolean.FALSE
                : actionCommand.equalsIgnoreCase(userCommand.trim());
    }
}
