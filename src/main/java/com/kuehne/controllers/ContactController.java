package com.kuehne.controllers;

import com.kuehne.dtos.ContactDto;
import com.kuehne.dtos.ContactResponseDto;
import com.kuehne.services.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller class to handle the request from UI
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/contact", consumes = "application/json", produces = "application/json")
@AllArgsConstructor
public class ContactController {

  private static final String DEFAULT_PAGE_VALUE = "0";
  private static final String DEFAULT_SIZE_VALUE = "10";
  private final ContactService contactService;

  /**
   * An endpoint to retrieve list of contacts
   *
   * @return List of {@link ContactDto}
   */
  @GetMapping("/")
  public ResponseEntity<ContactResponseDto<ContactDto>> list(
      @RequestParam(defaultValue = DEFAULT_PAGE_VALUE) int currentPage,
      @RequestParam(defaultValue = DEFAULT_SIZE_VALUE) int itemsPerPage) {
    return ResponseEntity.ok(contactService.list(currentPage, itemsPerPage));
  }

  /**
   * An endpoint to retrieve list of contacts by name
   *
   * @return List of {@link ContactDto}
   */
  @GetMapping("/name")
  public ResponseEntity<ContactResponseDto<ContactDto>> listByName(
      @RequestParam() String name,
      @RequestParam(defaultValue = DEFAULT_PAGE_VALUE) int currentPage,
      @RequestParam(defaultValue = DEFAULT_SIZE_VALUE) int itemsPerPage) {
    return ResponseEntity.ok(contactService.listByName(name, currentPage, itemsPerPage));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
    try {
      contactService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
