package com.phonebook.api.controller;

import com.phonebook.api.model.PhoneType;
import com.phonebook.api.service.PhoneTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneTypeController {
    @Autowired
    private PhoneTypeService service;

    @GetMapping("/types")
    ResponseEntity getAllPhoneTypes() {
        List<PhoneType> phoneTypes = service.getAllPhoneTypes();
        return new ResponseEntity<>(phoneTypes, HttpStatus.OK);
    }

    @PostMapping("/types")
    ResponseEntity postPhoneType(@RequestBody PhoneType newPhoneType) {
        service.createPhoneType(newPhoneType);
        return new ResponseEntity("Phone type created successfully", HttpStatus.OK);
    }

    @PutMapping("/types/{id}")
    ResponseEntity putPhoneType(@PathVariable Long id, @RequestBody @Valid PhoneType phoneType) {
        if(service.updatePhoneType(id, phoneType))
            return new ResponseEntity("Phone type updated successfully", HttpStatus.OK);
        else return new ResponseEntity("Phone type with this ID doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/types/{id}")
    ResponseEntity deletePhoneType(@PathVariable Long id) {
        if(service.deletePhoneType(id))
            return new ResponseEntity("Phone type deleted successfully", HttpStatus.OK);
        else return new ResponseEntity("Phone type with this ID doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
