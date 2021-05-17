package com.hitomi.hop.catalog.model.services.common;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class PageBlock<T> {

    @NonNull
    private List<T> items;

    @NonNull
    private int totalPages;

}
