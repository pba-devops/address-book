package pba.devops.addressbook.ui.view.action.command;

import pba.devops.addressbook.dataresolver.ContactRemote;
import pba.devops.addressbook.dataresolver.RemoteException;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.ui.view.ContactsView;
import pba.devops.addressbook.ui.view.action.command.abstracts.AbstractCommand;
import pba.devops.addressbook.utils.BeanProviderUtils;
import pba.devops.addressbook.utils.CommandUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteContactCommand extends AbstractCommand<Contact> {


    public DeleteContactCommand(String commandName) {
        super(commandName);
    }

    @Override
    public Boolean isMyJob(String userCommand) {

        // Command composed with 2 parts
        List<String> commandParts = CommandUtils.niceSplit(userCommand);
        if(commandParts.size() != 2) {
            return Boolean.FALSE;
        }
        // 1st part equals command name
        if(!commandName.equalsIgnoreCase(commandParts.get(0))) {
            return Boolean.FALSE;
        }
        // 2th part is an Integer
        if(!pba.devops.addressbook.model.utils.StringUtils.isInteger(commandParts.get(1))) {
            return Boolean.FALSE;
        }
        // 2th part has to be a contact id
        return
            BeanProviderUtils.getBean(ContactsView.class)
                .contactExists(Long.valueOf(commandParts.get(1)));
    }

    @Override
    public IJobDone doMyJob(String userCommand) {

        if(isMyJob(userCommand)) {

            Long contactId = contactId(userCommand);
            BeanProviderUtils.getBean(ContactRemote.class).delete(contactId);
            if(contactNotPresentAnymore(contactId)) {
                return new JobDone(Boolean.TRUE, "Deleted");
            }
            return
                new JobDone(Boolean.FALSE, "No Deleted ???");
        }
        return null;
    }

    /*
     * Private methods
     */

    private boolean contactNotPresentAnymore(Long id) {
        try {
            return
                BeanProviderUtils.getBean(ContactRemote.class)
                    .entities()
                        .stream()
                        .noneMatch(contact -> contact.getId().equals(id));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Long contactId(String userCommand) {
        Long contactRank =
            Long.valueOf(
                Arrays.stream(userCommand.trim().split(" "))
                    .collect(Collectors.toList()).get(1).trim());
        if(BeanProviderUtils.getBean(ContactsView.class).contactExists(contactRank)) {
            return
                BeanProviderUtils.getBean(ContactsView.class).contact(contactRank)
                    .getId();
        }
        return null; // :(
    }
}
