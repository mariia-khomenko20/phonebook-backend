package com.phonebook.api.dto;

import com.phonebook.api.model.ContactGroup;
import lombok.Data;

import java.util.List;

@Data
public class SetGroupDto {
    List<Long> contactIds;
    ContactGroup contactGroup;
}
