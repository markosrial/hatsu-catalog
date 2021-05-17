package com.hitomi.hop.catalog.rest.dto.common;

import com.hitomi.hop.catalog.model.services.exceptions.InstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ErrorConverter {

    @Autowired
    private MessageSource messageSource;

    public ErrorsDto toErrorsDto(InstanceException exception, String exceptionCode, Locale locale) {
        String name = messageSource.getMessage(exception.getName(), null, exception.getName(), locale);
        String property = messageSource.getMessage(exception.getProperty(), null, exception.getProperty(), locale);
        String errorMessage = messageSource.getMessage(
                exceptionCode,
                new Object[]{name, property, exception.getKey().toString()},
                exceptionCode, locale);

        return new ErrorsDto(errorMessage);
    }

}
