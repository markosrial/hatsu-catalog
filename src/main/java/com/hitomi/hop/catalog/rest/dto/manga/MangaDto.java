package com.hitomi.hop.catalog.rest.dto.manga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MangaDto {

    private String sku;
    private String title;
    private String blurb;
    private short pages;
    private LocalDate published;
    private Integer serieId;
    private Integer editorialId;
    private List<Short> genres;
    private List<Integer> artists;
    private List<Integer> writers;
    private String coverImage;
    private String backCoverImage;

    private LocalDateTime inserted;
    private LocalDateTime updated;

}
