package com.hitomi.hop.catalog.model.services.common;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Block<T> {

    @NonNull
    private List<T> items;

    @NonNull
    private boolean hasNext;

}
