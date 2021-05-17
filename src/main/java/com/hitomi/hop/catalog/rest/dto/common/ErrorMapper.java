package com.hitomi.hop.catalog.rest.dto.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.FieldError;

@Mapper
public interface ErrorMapper {

    ErrorMapper INSTANCE = Mappers.getMapper(ErrorMapper.class);

    @Mapping(source = "field", target = "fieldName")
    @Mapping(source = "defaultMessage", target = "message")
    FieldErrorDto toFieldErrorDto(FieldError fieldError);

}
