package com.hitomi.hop.catalog.rest.dto.mangaka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class MangakaDto {

    private Integer id;
    private String name;
    private LocalDate birthdate;
    private String isoCountry;
    private String image;

    private LocalDateTime inserted;
    private LocalDateTime updated;

}
