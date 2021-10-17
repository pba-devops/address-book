package pba.devops.addressbook.dataresolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.abstracts.INamable;
import pba.devops.addressbook.model.error.RemoteError;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.List;

public abstract class AbstractRemote<ENTITY,ID> implements IRemote<ENTITY,ID> {

    abstract protected String resource();

    @Override
    public String add(ENTITY entity) {
        try {
            return
                    post(new ObjectMapper().writeValueAsString(entity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public String delete(ID id) {
        return
            ClientBuilder.newClient()
                .target(resource().concat("/").concat(id.toString()))
                .request(MediaType.APPLICATION_JSON)
                .delete().readEntity(String.class);
    }

    @Override
    public String update(ENTITY entity) {
        try {
            return
                    post(new ObjectMapper().writeValueAsString(entity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /*
     * Protected methods
     */

    protected String get() {
        return
            ClientBuilder.newClient()
                .target(resource())
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(String.class);
    }

    protected String get(String parameter) {
        return
            ClientBuilder.newClient()
                .target(resource().concat(parameter))
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(String.class);
    }

    private String put(String requestBody) {
        return
            ClientBuilder.newClient()
            .target(resource())
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.json(requestBody), String.class);
    }

    protected String post(String requestBody) {
        return
            ClientBuilder.newClient()
                .target(resource())
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(requestBody), String.class);
    }



    private void restCall() throws Exception {


        String jsonResponse =
                ClientBuilder.newClient()
                    .target("http://localhost:8080/contact")
                    .request(MediaType.APPLICATION_JSON)
                    .get().readEntity(String.class);
//                        .post(Entity.json(new ObjectMapper().writeValueAsString(contacts))).readEntity(String.class);


        List<Contact> contacts = new ObjectMapper().readValue(jsonResponse, new TypeReference<List<Contact>>() {});
        System.out.println(contacts);

        String json = new ObjectMapper().writeValueAsString(contacts);
        System.out.println(json);
    }
}
