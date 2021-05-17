package com.hitomi.hop.catalog.model.services.exceptions;

public class InstanceNotFoundException extends InstanceException {

    public InstanceNotFoundException(String name, String property, Object key) {
        super(name, property, key);
    }

}
