package pba.devops.addressbook.repository;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import pba.devops.addressbook.model.*;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.enums.FamilyRelationship;

import java.io.File;

public class ContactRepositoryIT {

    @Test
    public void dataFilePopulate() {

        ContactRepository contactRepository = new ContactRepository();

        Assert.assertTrue(
            "DataFile ".concat(contactRepository.dataFile()).concat(" hasn't been created"),
            new File(contactRepository.dataFile()).exists());

        Contact acquaintance = new Acquaintance();
        acquaintance.setName("Edy");
        acquaintance.setSurname("Hulth");
        acquaintance.setEmail("eh@something.com");
        acquaintance.setPhone("33399282822");
        contactRepository.add(acquaintance);

        acquaintance = new Acquaintance();
        acquaintance.setName("Eliot");
        acquaintance.setSurname("Nutari");
        acquaintance.setEmail("dssdf@gmail.com");
        acquaintance.setPhone("999111827");
        contactRepository.add(acquaintance);

        Friend friend = new Friend();
        friend.setName("Jerome");
        friend.setSurname("Corser");
        friend.setEmail("div@gmail.com");
        friend.setPhone("099009776");
        friend.setMeeting(1980);
        contactRepository.add(friend);

        friend = new Friend();
        friend.setName("Ermos");
        friend.setSurname("Maden");
        friend.setEmail("teels@gmail.com");
        friend.setPhone("33344334");
        friend.setMeeting(2008);
        contactRepository.add(friend);

        Family family = new Family();
        family.setName("Carine");
        family.setSurname("Grune");
        family.setEmail("cg@something.com");
        family.setPhone("22441151");
        family.setRelationship(FamilyRelationship.COUSIN);
        contactRepository.add(family);

        family = new Family();
        family.setName("Tanos");
        family.setSurname("Valmir");
        family.setEmail("qbuiol@gmail.com");
        family.setPhone("77664444");
        family.setRelationship(FamilyRelationship.NEPHEW);
        contactRepository.add(family);
    }

    @Ignore
    @Test
    public void findAllTesting() {

        new ContactRepository().readAll()
            .stream().forEach((contact ->
                System.out.println(contact.getId() + " - " + contact.getName() + " - " + contact.getSurname())));
    }

    @Ignore
    @Test
    public void findNameTesting() {

        new ContactRepository().readByName("A")
            .stream().forEach((contact ->
                System.out.println(contact.getId() + " - " + contact.getName() + " - " + contact.getSurname())));
    }

    @Ignore
    @Test
    public void updateContactTesting() {

        Friend friend = new Friend();
        friend.setId(2L);
        friend.setName("Puiscal");
        friend.setSurname("Perwin");
        friend.setEmail("pr@something.com");
        friend.setPhone("+352 229 332 111");
        friend.setAge(36);
        friend.setMeeting(1986);
        new ContactRepository().update(friend);
    }

    @Ignore
    @Test
    public void deleteContactTesting() {

        Friend friend = new Friend();
        friend.setId(3L);
        friend.setName("Puiscal");
        friend.setSurname("Perwin");
        friend.setEmail("pr@something.com");
        friend.setPhone("+352 229 332 111");
        friend.setAge(36);
        friend.setMeeting(1986);
        new ContactRepository().remove(friend);
    }
}
