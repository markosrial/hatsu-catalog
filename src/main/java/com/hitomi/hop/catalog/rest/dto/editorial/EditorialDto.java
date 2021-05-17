package com.hitomi.hop.catalog.rest.dto.editorial;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class EditorialDto {

    private Integer id;
    private String name;
    private String isoCountry;
    private String website;
    private String logo;

    private LocalDateTime inserted;
    private LocalDateTime updated;

}
