package com.kuehne.services;

import com.kuehne.dtos.ContactDto;
import com.kuehne.dtos.ContactResponseDto;
import com.kuehne.entities.Contact;
import com.kuehne.exceptions.ExceptionCode;
import com.kuehne.exceptions.RequirementException;
import com.kuehne.mappers.ContactMapper;
import com.kuehne.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * A service class to process contact requests and handle the entity-DTO transformation
 */
@Service
@RequiredArgsConstructor
public class ContactService {

  private final ContactRepository contactRepository;

  public ContactResponseDto<ContactDto> list(int currentPage, int itemsPerPage) {
    var pageContacts = contactRepository.findAll(PageRequest.of(currentPage, itemsPerPage));
    return getPagingResponse(pageContacts);
  }

  public ContactResponseDto<ContactDto> listByName(String name, int currentPage, int itemsPerPage) {
    if (Objects.isNull(name) || name.isBlank()) {
      throw new RequirementException(ExceptionCode.NAME_IS_EMPTY);
    }
    var pageContacts = contactRepository.findByNameContainsIgnoreCase(name,
        PageRequest.of(currentPage, itemsPerPage));
    return getPagingResponse(pageContacts);
  }

  private ContactResponseDto<ContactDto> getPagingResponse(Page<Contact> pageContacts) {
    var contactDtoList = ContactMapper.INSTANCE.toDtoList(pageContacts.getContent());

    var contactResponseDto = new ContactResponseDto<ContactDto>();
    contactResponseDto.setContacts(contactDtoList);
    contactResponseDto.setCurrentPage(pageContacts.getNumber());
    contactResponseDto.setTotalItems(pageContacts.getTotalElements());
    contactResponseDto.setTotalPages(pageContacts.getTotalPages());

    return contactResponseDto;
  }

  public void deleteById(long id) {
    contactRepository.deleteById(id);
  }
}
