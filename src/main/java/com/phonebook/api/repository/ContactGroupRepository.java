package com.phonebook.api.repository;

import com.phonebook.api.model.ContactGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactGroupRepository extends JpaRepository<ContactGroup, Long> {
    ContactGroup findByName(String name);
}
