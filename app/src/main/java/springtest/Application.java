package springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springtest.api.Contact;
import springtest.api.ContactRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"springtest.api"})
//@EntityScan(basePackages = {"springtest.api"})
public class Application {

    @Autowired
    private ContactRepository contactRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void testPrintAccountId() {
        contactRepository.save(new Contact("123"));
        contactRepository.save(new Contact("456"));
        List<Contact> all = contactRepository.findAll();
        for (Contact c : all) {
            System.out.println("userid: " + c.getUserId());
        }
    }

}
