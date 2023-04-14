package com.phonebook.api.service;

import com.phonebook.api.dto.ContactDto;
import com.phonebook.api.dto.ContactForListDto;
import com.phonebook.api.model.Contact;
import com.phonebook.api.model.ContactGroup;
import com.phonebook.api.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;
    @Autowired
    private DtoMapper dtoMapper;

    public Integer getPagesCountOfContacts(Integer size) {
        Double count = Double.valueOf(repository.count());
        return Double.valueOf(Math.ceil(count / size)).intValue();
    }

    public List<ContactForListDto> getContactsPage(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(contact ->
                        dtoMapper.toContactForListDto(contact))
                .collect(Collectors.toList());
    }

    public ContactDto getContactById(Long id) {
        if(repository.existsById(id)) {
            Contact contact = repository.findById(id).orElseThrow();
            return dtoMapper.toContactDto(contact);
        } else return null;
    }

    public Boolean createContact(ContactDto contactDto) {
        Contact newContact = dtoMapper.fromContactDto(contactDto);
        repository.save(newContact);
        return true;
    }

    public Boolean updateContact(Long id, ContactDto contactDto) {
        if(repository.existsById(id)) {
            Contact contact = dtoMapper.fromContactDto(contactDto);
            contact.setId(id);
            repository.save(contact);
            return true;
        } else return false;
    }

    public Boolean deleteContact(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else return false;
    }
}
