package com.kuehne.contact.services;

import com.kuehne.entities.Contact;
import com.kuehne.repositories.ContactRepository;
import com.kuehne.services.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

  @Mock
  private ContactRepository contactRepository;
  private ContactService contactService;
  private List<Contact> contactList;

  @BeforeEach
  public void init() {
    contactService = new ContactService(contactRepository);

    var contact1 = new Contact();
    contact1.setId(1);
    contact1.setName("First Contact");
    contact1.setUrl(
        "https://vignette.wikia.nocookie.net/simpsons/images/b/bd/Homer_Simpson.png/revision/latest/scale-to-width-down/72?cb=20140126234206");

    var contact2 = new Contact();
    contact2.setId(2);
    contact2.setName("Second Contact");
    contact2.setUrl(
        "https://vignette.wikia.nocookie.net/simpsons/images/4/4d/MargeSimpson.png/revision/latest/scale-to-width-down/78?cb=20180314071936");

    contactList = new ArrayList<>();
    contactList.add(contact1);
    contactList.add(contact2);
  }

  @Test
  void shouldReturnAllContacts() {
    Page<Contact> pageContact = new PageImpl<>(contactList);
    when(contactRepository.findAll(PageRequest.of(0, 10))).thenReturn(pageContact);

    var resultMap = contactService.list(0, 10);
    var resultName = resultMap.getContacts().get(0).getName();
    assertThat(resultName).isEqualTo("First Contact");
  }

  @Test
  void shouldReturnContactsWithGivenName() {
    var name = "first";

    Page<Contact> pageContact = new PageImpl<>(contactList);
    when(contactRepository.findByNameContainsIgnoreCase(name, PageRequest.of(0, 10))).thenReturn(
        pageContact);

    var resultMap = contactService.listByName(name, 0, 10);
    var resultName = resultMap.getContacts().get(0).getName();
    assertThat(resultName).isEqualTo("First Contact");
  }
}
