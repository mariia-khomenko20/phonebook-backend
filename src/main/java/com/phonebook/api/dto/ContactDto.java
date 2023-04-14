package com.phonebook.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class ContactDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String group;
    private List<PhoneDto> phones;
}
