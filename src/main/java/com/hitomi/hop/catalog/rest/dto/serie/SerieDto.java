package com.hitomi.hop.catalog.rest.dto.serie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class SerieDto {

    private Integer id;
    private String name;
    private String description;
    private String image;

    private LocalDateTime inserted;
    private LocalDateTime updated;

}
