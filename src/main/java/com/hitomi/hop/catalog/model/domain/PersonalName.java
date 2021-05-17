package com.hitomi.hop.catalog.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class PersonalName {

    private String firstName;
    private String middleName;
    private String lastName;

    @Transient
    public String getFullName() {
        StringBuilder builder = new StringBuilder(firstName);
        if (middleName != null) {
            builder.append(" ").append(middleName);
        }
        builder.append(" ").append(lastName);
        return builder.toString();
    }

    @Override
    public String toString() {
        return getFullName();
    }

}
