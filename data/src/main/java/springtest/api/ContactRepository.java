package springtest.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String> {

    void deleteByUserId(String s);
}
