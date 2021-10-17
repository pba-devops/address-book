package pba.devops.addressbook.ui.view.abstracts;

import pba.devops.addressbook.model.abstracts.Contact;

import java.util.Map;

public interface IContactsView extends IView {

    Boolean contactExists(Long id);
    Contact contact(Long id);
}
