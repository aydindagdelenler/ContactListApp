package com.kuehne.dtos;

import java.util.List;

/**
 * A record covering and transferring contact response
 */
public record ContactResponseDto<ContactDto>(int currentPage, int totalPages, long totalItems, List<ContactDto> contacts) {
}