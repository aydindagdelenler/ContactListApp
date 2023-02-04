package com.kuehne.contact.repositories;

import com.kuehne.entities.Contact;
import com.kuehne.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ContactRepositoryTest {

  @Autowired
  private ContactRepository contactRepository;

  @BeforeEach
  void setUp() {
    contactRepository.deleteAll();

    var contact1 = new Contact();
    contact1.setId(1);
    contact1.setName("First Contact");
    contact1.setUrl("https://vignette.wikia.nocookie.net/simpsons/images/b/bd/Homer_Simpson.png/revision/latest/scale-to-width-down/72?cb=20140126234206");

    var contact2 = new Contact();
    contact2.setId(2);
    contact2.setName("Second Contact");
    contact2.setUrl(
        "https://vignette.wikia.nocookie.net/simpsons/images/4/4d/MargeSimpson.png/revision/latest/scale-to-width-down/78?cb=20180314071936\t");

    contactRepository.saveAll(List.of(contact1, contact2));
  }

  @Test
  void givenGetAllContactsShouldReturnListOfAllContacts(){
    var contactPage = contactRepository.findAll(PageRequest.of(0, 10));
    assertEquals(2, contactPage.getContent().size());
  }

  @Test
  void givenGetAllContactsShouldReturnListOfAllContactsByName(){
    var contactPage = contactRepository.findByNameContainsIgnoreCase("first",PageRequest.of(0, 10));
    assertEquals("First Contact", contactPage.getContent().get(0).getName());
  }
}
