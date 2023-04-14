package com.phonebook.api.repository;

import com.phonebook.api.model.Phone;
import com.phonebook.api.model.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhoneTypeRepository extends JpaRepository<PhoneType, Long> {
    PhoneType findByName(String name);
}
