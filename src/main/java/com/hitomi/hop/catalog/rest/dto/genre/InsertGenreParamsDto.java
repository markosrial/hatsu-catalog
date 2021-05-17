package com.hitomi.hop.catalog.rest.dto.genre;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter @Setter
public class InsertGenreParamsDto {

    @NotNull
    @Size(min=1, max=30)
    private String name;

}
