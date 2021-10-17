package pba.devops.addressbook.repository;

import org.springframework.stereotype.Repository;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.repository.abstracts.AbstractJsonRepository;
import pba.devops.addressbook.repository.abstracts.ILatestIdRecorder;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository("contactRepository")
public class ContactRepository extends AbstractJsonRepository<Contact,Long> {

    @Override
    protected String dataFile() {
        return "./addressBook.json";
    }

    @Override
    protected List<Contact> sortedEntities(List<Contact> contacts) {
        return
            contacts.stream().sorted(
                (contact1, contact2) -> {
                    return contact1.getSurname().toLowerCase()
                        .compareTo(contact2.getSurname().toLowerCase());
                }).collect(Collectors.toList());
    }

    @Override
    protected Class<Contact> clazz() {
        return Contact.class;
    }

    @Override
    protected ILatestIdRecorder<Long> latestIdRecorder() {
        return
            new ILatestIdRecorder<Long>() {
                private Long lastId = 0L;
                @Override
                public void record(Long id) {
                    if(id > lastId) {
                        lastId = id;
                    }
                }
                @Override
                public Long nextId() {
                    return lastId + 1;
                }
            };
    }
}
