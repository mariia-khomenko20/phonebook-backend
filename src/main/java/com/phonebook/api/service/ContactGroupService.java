package com.phonebook.api.service;

import com.phonebook.api.model.ContactGroup;
import com.phonebook.api.repository.ContactGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactGroupService {
    @Autowired
    private ContactGroupRepository repository;

    public List<ContactGroup> getAllContactGroups() {
        return repository.findAll();
    }

    public Boolean createContactGroup(ContactGroup newContactGroup) {
        repository.save(newContactGroup);
        return true;
    }

    public Boolean updateContactGroup(Long id, ContactGroup contactGroup) {
        if(repository.existsById(id)) {
            contactGroup.setId(id);
            repository.save(contactGroup);
            return true;
        } else return false;
    }

    public Boolean deleteContactGroup(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else return false;
    }
}
