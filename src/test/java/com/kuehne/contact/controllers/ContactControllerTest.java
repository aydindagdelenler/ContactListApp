package com.kuehne.contact.controllers;

import com.kuehne.controllers.ContactController;
import com.kuehne.dtos.ContactDto;
import com.kuehne.dtos.ContactResponseDto;
import com.kuehne.services.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

  @MockBean
  private ContactService contactService;

  @Autowired
  private MockMvc mockMvc;

  private ContactResponseDto<ContactDto> contactResponseDto;

  @BeforeEach
  public void init() {
    var contact1 = new ContactDto();
    contact1.setId(1);
    contact1.setName("First Contact");
    contact1.setUrl(
            "https://vignette.wikia.nocookie.net/simpsons/images/b/bd/Homer_Simpson.png/revision/latest/scale-to-width-down/72?cb=20140126234206");

    var contact2 = new ContactDto();
    contact2.setId(2);
    contact2.setName("Second Contact");
    contact2.setUrl(
            "https://vignette.wikia.nocookie.net/simpsons/images/4/4d/MargeSimpson.png/revision/latest/scale-to-width-down/78?cb=20180314071936\t");

    contactResponseDto = new ContactResponseDto<>();
    contactResponseDto.setContacts(List.of(contact1, contact2));
    contactResponseDto.setCurrentPage(0);
    contactResponseDto.setTotalItems(10);
    contactResponseDto.setTotalPages(1);
  }

  @Test
  void shouldReturnAllContacts() throws Exception {
    when(contactService.list(0, 10)).thenReturn(contactResponseDto);

    mockMvc
        .perform(
            get("/contact/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "{\"currentPage\":0,\"totalPages\":1,\"totalItems\":10,\"contacts")));
  }

  @Test
  void shouldReturnContactsWithGivenName() throws Exception {
    var name = "first";

    when(contactService.listByName(name, 0, 10)).thenReturn(contactResponseDto);

    mockMvc
        .perform(
            get("/contact/name?name=" + name)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "First Contact")));
  }
}
