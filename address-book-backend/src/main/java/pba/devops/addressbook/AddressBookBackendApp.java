package pba.devops.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AddressBookBackendApp {
    static final public void main(String[] args) {
        SpringApplication.run(AddressBookBackendApp.class);
    }
}