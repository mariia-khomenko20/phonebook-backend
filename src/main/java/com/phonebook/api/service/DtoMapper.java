package com.phonebook.api.service;

import com.phonebook.api.dto.ContactDto;
import com.phonebook.api.dto.ContactForListDto;
import com.phonebook.api.dto.PhoneDto;
import com.phonebook.api.model.Contact;
import com.phonebook.api.model.ContactGroup;
import com.phonebook.api.model.Phone;
import com.phonebook.api.model.PhoneType;
import com.phonebook.api.repository.ContactGroupRepository;
import com.phonebook.api.repository.PhoneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoMapper {
    @Autowired
    private ContactGroupRepository contactGroupRepository;

    @Autowired
    private PhoneTypeRepository phoneTypeRepository;

    public PhoneDto toPhoneDto(Phone phone) {
        PhoneDto phoneDto = new PhoneDto();

        phoneDto.setId(phone.getId());
        phoneDto.setNumber(phone.getNumber());
        phoneDto.setType(phone.getType().getName());

        return phoneDto;
    }

    public Phone fromPhoneDto(PhoneDto phoneDto) {
        Phone phone = new Phone();

        phone.setId(phone.getId());
        phone.setNumber(phoneDto.getNumber());

        PhoneType type = phoneTypeRepository.findByName(phoneDto.getType());
        phone.setType(type);

        return phone;
    }

    public ContactDto toContactDto(Contact contact) {
        ContactDto contactDto = new ContactDto();

        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setEmail(contact.getEmail());
        contactDto.setGroup(contact.getGroup().getName());

        List<PhoneDto> phones = contact.getPhones().stream().map(phone -> toPhoneDto(phone)).collect(Collectors.toList());
        contactDto.setPhones(phones);

        return contactDto;
    }

    public Contact fromContactDto(ContactDto contactDto) {
        Contact contact = new Contact();

        contact.setId(contactDto.getId());
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setEmail(contactDto.getEmail());

        ContactGroup group = contactGroupRepository.findByName(contactDto.getGroup());
        contact.setGroup(group);

        List<Phone> phones = contactDto.getPhones().stream().map(phoneDto -> {
            Phone phone = fromPhoneDto(phoneDto);
            phone.setContact(contact);
            return phone;
        }).collect(Collectors.toList());
        contact.setPhones(phones);

        return contact;
    }

    public ContactForListDto toContactForListDto(Contact contact) {
        ContactForListDto contactForListDto = new ContactForListDto();
        contactForListDto.setId(contact.getId());
        contactForListDto.setFullName(String.format("%s %s", contact.getFirstName(), contact.getLastName()));
        return contactForListDto;
    }
}
