package com.kuehne.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception codes and their descriptions to be returned
 * when the custom exception is thrown
 */
@Getter
@AllArgsConstructor
public enum ExceptionCode {
  NAME_IS_EMPTY("Name as a search parameter cannot be empty");

  private final String description;
}
