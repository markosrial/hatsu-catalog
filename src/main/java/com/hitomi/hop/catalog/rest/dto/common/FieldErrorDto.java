package com.hitomi.hop.catalog.rest.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class FieldErrorDto {

    private String fieldName;
    private String message;

}
