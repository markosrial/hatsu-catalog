package com.hitomi.hop.catalog.model.services.exceptions;

public class DuplicatedInstanceException extends InstanceException {

    public DuplicatedInstanceException(String name, String property, Object key) {
        super(name, property, key);
    }

}
