package pba.devops.addressbook.ui.view;

import org.springframework.stereotype.Component;
import pba.devops.addressbook.dataresolver.RemoteException;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.utils.StringUtils;
import pba.devops.addressbook.ui.view.abstracts.AbstractView;
import pba.devops.addressbook.ui.view.abstracts.IContactsView;
import pba.devops.addressbook.ui.view.action.Action;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component("contactsView")
public class ContactsView extends AbstractView implements IContactsView {

    final private Map<Long,Contact> contacts;

    public ContactsView() {
        super(Arrays.asList(
            Action.VIEW_NEW_CONTACT,
            Action.VIEW_UPDATE_CONTACT,
            Action.DELETE_CONTACT,
            Action.VIEW_OF_MENU,
            Action.VIEW_QUIT));
        this.contacts = new HashMap<Long, Contact>();
    }

    @Override
    protected void initialize(Object workflowData) {
        try {

            contacts.clear();

            headerDisplay();

            AtomicLong index = new AtomicLong(0);
            contactRemote.entities().stream()
                .forEach(entity -> {

                    contacts.put(entity.getId(), entity);

                    System.out.format(
                        StringUtils.fixedSizeString("",13).concat("%s%s \n"),
                        StringUtils.fixedSizeString(index.incrementAndGet(), 2),
                        entity);
                });

            System.out.println();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact contact(Long id) {
        return contacts.get(id);
    }

    @Override
    public Boolean contactExists(Long id) {
        return contacts.containsKey(id);
    }

    private void headerDisplay() {
        String header =
                StringUtils.fixedSizeString("N°", 2) +
                        "   " + StringUtils.fixedSizeString("[ ID ]", 6) +
                        "   " + StringUtils.fixedSizeString("[ CATEGORY ]", 13) +
                        "   " + StringUtils.fixedSizeString("[ SURNAME ]", 11) +
                        "   " + StringUtils.fixedSizeString("[ NAME ]", 8) +
                        "   " + StringUtils.fixedSizeString("[ PHONE ]", 13) +
                        "   " + StringUtils.fixedSizeString("[ EMAIL ]", 10) +
                        "   " + StringUtils.fixedSizeString("[ AGE ]", 10) +
                        "   " + StringUtils.fixedSizeString("[ HAIR ]", 9) +
                        "   " + StringUtils.fixedSizeString("[ RELATIONSHIP ] / YEAR OF [ MEETING ]", 40) + "   ";
        String underlines =
                StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_","N°".length()), 2) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ ID ]".length()), 6) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ CATEGORY ]".length()), 13) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ SURNAME ]".length()), 11) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ NAME ]".length()), 8) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ PHONE ]".length()), 13) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ EMAIL ]".length()), 10) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ AGE ]".length()), 10) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ HAIR ]".length()), 9) +
                        "   " + StringUtils.fixedSizeString(StringUtils.nTimesCharacters("_", "[ RELATIONSHIP ] / YEAR OF [ MEETING ]".length()), 40) + "   ";
        System.out.format(
            StringUtils.fixedSizeString("\n",14).concat("%s\n%s \n\n"),
            header,
                StringUtils.fixedSizeString("",13).concat(underlines));
    }
}