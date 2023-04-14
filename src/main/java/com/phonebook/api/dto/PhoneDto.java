package com.phonebook.api.dto;

import lombok.Data;

@Data
public class PhoneDto {
    private Long id;
    private String number;
    private String type;
}
