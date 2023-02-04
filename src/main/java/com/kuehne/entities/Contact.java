package com.kuehne.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * An entity class covering information of a contact stored in the database
 * and transferring data from/to the relevant DTO
 */
@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotBlank
  @Column(name = "name")
  private String name;

  @NotBlank
  @Column(name = "url")
  private String url;
}
