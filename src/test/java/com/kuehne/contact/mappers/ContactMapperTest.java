package com.kuehne.contact.mappers;

import com.kuehne.entities.Contact;
import com.kuehne.mappers.ContactMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactMapperTest {

    @Test
    void toDtoList() {
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

        var contactDtoList = ContactMapper.INSTANCE.toDtoList(List.of(contact1, contact2));
        assertThat(contactDtoList).hasSize(2);
    }
}
