package pba.devops.addressbook.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.rest.abstracts.Rest;
import pba.devops.addressbook.service.abstracts.IContactService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {
    ContactRest.ORIGIN_OF_BACKEND,
    ContactRest.ORIGIN_OF_COMMAND_LINE_UI,
    ContactRest.ORIGIN_OF_REACTJS_UI})
@RestController
@RequestMapping("/contact")
public class ContactRest extends Rest {

    @Autowired
    @Qualifier("contactService")
    private IContactService<Contact, Long> contactService;

    public ContactRest() {}

    @GetMapping
    public List<Contact> contacts() {
        return contactService.readAll();
    }

    @GetMapping("/{name}")
    public List<Contact> contactByName(@PathVariable String name) {
        return contactService.readByName(name);
    }

    @PutMapping
    public Contact add(@Valid @RequestBody Contact contact) {
        return
            contactService.add(contact);
    }

    @PostMapping
    public Contact update(@Valid @RequestBody Contact contact) {
        return
            contactService.update(contact);
    }

    @DeleteMapping("/{id}")
    public void remove(@Valid @PathVariable Long id) {
        contactService.remove(id);
    }
}
