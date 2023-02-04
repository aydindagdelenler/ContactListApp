package com.kuehne.repositories;

import com.kuehne.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * A JpaRepository to handle database operations of entities
 */
@Repository
@Transactional(readOnly = true)
public interface ContactRepository extends JpaRepository<Contact, Long> {

  Page<Contact> findAll(Pageable pageable);
  Page<Contact> findByNameContainsIgnoreCase(String name, Pageable pageable);
}
