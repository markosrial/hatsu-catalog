package com.hitomi.hop.catalog.validation.constraints;

import com.hitomi.hop.catalog.i18n.I18N;
import com.hitomi.hop.catalog.validation.constraintvalidators.IsoCountryCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Locale;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(IsoCountryCode.List.class)
@Constraint(validatedBy = {IsoCountryCodeValidator.class})
@Documented
public @interface IsoCountryCode {
    String message() default "{" + I18N.VALIDATION_ISO_COUNTRY_CODE + "}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

    Locale.IsoCountryCode value() default Locale.IsoCountryCode.PART1_ALPHA2;

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        IsoCountryCode[] value();
    }
}
