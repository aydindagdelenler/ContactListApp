package com.kuehne.mappers;

import com.kuehne.dtos.ContactDto;
import com.kuehne.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * A mapper class to handle entity-DTO transformation
 */
@Mapper(componentModel = "spring")
public interface ContactMapper {
  ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);
  List<ContactDto> toDtoList(List<Contact> contactList);
}
