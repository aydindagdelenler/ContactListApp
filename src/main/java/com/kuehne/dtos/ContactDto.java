package com.kuehne.dtos;

/**
 * A record covering and transferring contact information from/to entity: contact ID, contact name,
 * and contact url
 */
public record ContactDto(long id, String name, String url) {
}