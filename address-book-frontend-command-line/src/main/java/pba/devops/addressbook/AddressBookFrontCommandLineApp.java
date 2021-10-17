package pba.devops.addressbook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.ui.view.abstracts.IView;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

@SpringBootApplication
public class AddressBookFrontCommandLineApp implements CommandLineRunner {

    @Autowired
    @Qualifier("welcomeView")
    private IView welcomeView;

    public static void main(String[] args) {
        SpringApplication.run(AddressBookFrontCommandLineApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        welcomeView.load();

    }

}
