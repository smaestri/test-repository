package springtest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import springtest.api.ContactRepository;
import springtest.api.Contact;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes={Contact.class })
@EnableAutoConfiguration
public class DB_Only_Test {

    @Autowired
    ContactRepository contactRespository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void testWithFrenchIsbn() {

        // given
        Contact contact = new Contact();
        contact.setUserId("123");
        contact.setAddress("address1");
        testEntityManager.persist(contact);
        Contact contact2= new Contact();
        contact2.setUserId("456");
        contact2.setAddress("address5");
        testEntityManager.persist(contact2);
        List<Contact> c = contactRespository.findAll();
        assertEquals(c.size(), 2);

        // when
        contactRespository.deleteByUserId("123");
        List<Contact> all = contactRespository.findAll();

        // then
        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getAddress(), "address5");

    }

}