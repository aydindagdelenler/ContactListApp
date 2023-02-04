package com.kuehne.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * A DTO covering and transferring contact information from/to entity: contact ID, contact name,
 * and contact url
 */
@Getter
@Setter
public class ContactDto {

  private long id;
  private String name;
  private String url;

}
