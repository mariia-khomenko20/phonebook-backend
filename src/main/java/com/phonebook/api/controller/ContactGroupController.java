package com.phonebook.api.controller;

import com.phonebook.api.model.ContactGroup;
import com.phonebook.api.service.ContactGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactGroupController {
    @Autowired
    private ContactGroupService service;

    @GetMapping("/groups")
    ResponseEntity getAllContactGroups() {
        List<ContactGroup> contactGroups = service.getAllContactGroups();
        return new ResponseEntity(contactGroups, HttpStatus.OK);
    }

    @PostMapping("/groups")
    ResponseEntity postContactGroup(@RequestBody ContactGroup newContactGroup) {
        service.createContactGroup(newContactGroup);
        return new ResponseEntity("Contact group created successfully", HttpStatus.OK);
    }

    @PutMapping("/groups/{id}")
    ResponseEntity putContactGroup(@PathVariable Long id, @RequestBody @Valid ContactGroup contactGroup) {
        if(service.updateContactGroup(id, contactGroup))
            return new ResponseEntity<>("Contact group updated successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Contact group with this ID doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/groups/{id}")
    ResponseEntity deleteContactGroup(@PathVariable Long id) {
        if(service.deleteContactGroup(id))
            return new ResponseEntity<>("Contact group deleted successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Contact group with this ID doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
