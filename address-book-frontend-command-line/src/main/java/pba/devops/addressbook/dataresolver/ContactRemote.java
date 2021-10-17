package pba.devops.addressbook.dataresolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.error.RemoteError;

import java.util.List;

@Component("contactRemote")
public class ContactRemote extends AbstractRemote<Contact,Long> {

    @Override
    protected String resource() {
        return "http://localhost:8080/contact";
    }

    @Override
    public List<Contact> entities() throws RemoteException {
        try {
            return new ObjectMapper().readValue(get(), new TypeReference<List<Contact>>() {});
        } catch (Exception e) {
            throw new RemoteException(new RemoteError());
        }
    }
}
