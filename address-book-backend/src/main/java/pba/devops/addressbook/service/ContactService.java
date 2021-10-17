package pba.devops.addressbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.repository.abstracts.IRepository;
import pba.devops.addressbook.service.abstracts.IContactService;

import java.util.List;

@Service("contactService")
public class ContactService implements IContactService<Contact,Long> {

    @Autowired
    @Qualifier("contactRepository")
    private IRepository<Contact, Long> contactRepository;

    @Override
    public Contact add(Contact addedEntity) {
        return
            contactRepository.add(addedEntity);
    }

    @Override
    public Contact update(Contact updatedEntity) {
        return
            contactRepository.update(updatedEntity);
    }

    @Override
    public void remove(Long id) {
        contactRepository.remove(contactRepository.readById(id));
    }

    @Override
    public List<Contact> readAll() {
        return contactRepository.readAll();
    }

    @Override
    public List<Contact> readByName(String partOfName) {
        return contactRepository.readByName(partOfName);
    }
}
