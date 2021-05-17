package com.hitomi.hop.catalog.rest.dto.manga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter @Setter
public class MangaSearchParamsDto {

    @Min(0)
    private int page = 0;

    @Min(10)
    @Max(100)
    private int size = 10;

}
