package com.phonebook.api.controller;

import com.phonebook.api.dto.ContactDto;
import com.phonebook.api.dto.ContactForListDto;
import com.phonebook.api.dto.SetGroupDto;
import com.phonebook.api.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService service;

    @GetMapping("/contacts/pages")
    ResponseEntity getPagesCountOfContacts(@RequestParam Integer size) {
        Integer pagesCount = service.getPagesCountOfContacts(size);
        return new ResponseEntity<>(pagesCount, HttpStatus.OK);
    }

    @GetMapping("/contacts")
    ResponseEntity getContactsPage(@RequestParam Integer page, @RequestParam Integer size) {
        List<ContactForListDto> contacts = service.getContactsPage(page, size);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    ResponseEntity getContactById(@PathVariable Long id) {
        ContactDto contactDto = service.getContactById(id);
        if(contactDto != null) return new ResponseEntity<>(contactDto, HttpStatus.OK);
        else return new ResponseEntity<>("Contact with this ID doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/contacts")
    ResponseEntity postContact(@RequestBody ContactDto contactDto) {
        service.createContact(contactDto);
        return new ResponseEntity<>("Contact created successfully", HttpStatus.OK);
    }

    @PutMapping("/contacts/{id}")
    ResponseEntity putContact(@PathVariable Long id, @RequestBody ContactDto contactDto) {
        if(service.updateContact(id, contactDto))
            return new ResponseEntity<>("Contact updated successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Contact with this ID doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/contacts/{id}")
    ResponseEntity deleteContact(@PathVariable Long id) {
        if(service.deleteContact(id))
            return new ResponseEntity<>("Contact deleted successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Contact with this ID doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
