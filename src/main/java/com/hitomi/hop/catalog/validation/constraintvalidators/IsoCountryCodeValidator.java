package com.hitomi.hop.catalog.validation.constraintvalidators;

import com.hitomi.hop.catalog.validation.constraints.IsoCountryCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class IsoCountryCodeValidator implements ConstraintValidator<IsoCountryCode, String> {

    private Locale.IsoCountryCode type;

    @Override
    public void initialize(IsoCountryCode constraintAnnotation) {
        type = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return s == null || Locale.getISOCountries(type).contains(s);
    }

}
