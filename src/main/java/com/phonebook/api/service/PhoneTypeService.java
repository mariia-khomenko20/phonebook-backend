package com.phonebook.api.service;

import com.phonebook.api.model.PhoneType;
import com.phonebook.api.repository.PhoneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneTypeService {
    @Autowired
    private PhoneTypeRepository repository;

    public List<PhoneType> getAllPhoneTypes() {
        return repository.findAll();
    }

    public Boolean createPhoneType(PhoneType newPhoneType) {
        repository.save(newPhoneType);
        return true;
    }

    public Boolean updatePhoneType(Long id, PhoneType phoneType) {
        if (repository.existsById(id)) {
            phoneType.setId(id);
            repository.save(phoneType);
            return true;
        } else return false;
    }

    public Boolean deletePhoneType(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else return false;
    }
}
