package com.kuehne.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactResponseDto<ContactDto> {
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private List<ContactDto> contacts;
}
