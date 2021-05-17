package com.hitomi.hop.catalog.rest.controller;

import com.hitomi.hop.catalog.i18n.I18N;
import com.hitomi.hop.catalog.model.services.exceptions.DuplicatedInstanceException;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;
import com.hitomi.hop.catalog.rest.dto.common.ErrorConverter;
import com.hitomi.hop.catalog.rest.dto.common.ErrorMapper;
import com.hitomi.hop.catalog.rest.dto.common.ErrorsDto;
import com.hitomi.hop.catalog.rest.dto.common.FieldErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CommonControllerAdvice {

    @Autowired
    private ErrorConverter errorConverter;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<FieldErrorDto> fieldErrors = exception.getBindingResult().getFieldErrors()
                .stream().map(ErrorMapper.INSTANCE::toFieldErrorDto).collect(Collectors.toList());

        return new ErrorsDto(fieldErrors);

    }

    @ExceptionHandler(DuplicatedInstanceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorsDto handleDuplicatedInstanceException(DuplicatedInstanceException exception, Locale locale) {
        return errorConverter.toErrorsDto(exception, I18N.DUPLICATED_INSTANCE_EXCEPTION, locale);
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDto handleInstanceNotFoundException(InstanceNotFoundException exception, Locale locale) {
        return errorConverter.toErrorsDto(exception, I18N.INSTANCE_NOT_FOUND_EXCEPTION, locale);
    }

}
