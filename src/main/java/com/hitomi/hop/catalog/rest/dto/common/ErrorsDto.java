package com.hitomi.hop.catalog.rest.dto.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter @Setter
public class ErrorsDto {

    private String globalError;
    private List<FieldErrorDto> fieldErrors;

    public ErrorsDto(String globalError) {
        this.globalError = globalError;
    }

    public ErrorsDto(List<FieldErrorDto> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public void setGlobalError(String globalError) {
        this.globalError = StringUtils.capitalize(globalError);
    }

}
