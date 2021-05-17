package com.hitomi.hop.catalog.model.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class InstanceException extends Exception {

    private final String name;
    private final String property;
    private final Object key;

}
