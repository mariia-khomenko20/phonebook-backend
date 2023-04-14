package com.phonebook.api.repository;

import com.phonebook.api.model.Contact;
import com.phonebook.api.model.ContactGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByGroup(ContactGroup contactGroup);
    List<Contact> findAllByFirstNameOrLastName(String firsName, String lastName);
    List<Contact> findAllByFirstNameAndLastName(String firstName, String lastName);
}
