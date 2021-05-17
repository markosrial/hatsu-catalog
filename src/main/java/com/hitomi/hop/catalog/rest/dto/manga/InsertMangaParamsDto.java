package com.hitomi.hop.catalog.rest.dto.manga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class InsertMangaParamsDto {

    public interface AddValidations {}
    public interface UpdateValidations {}

    @NotNull(groups = {AddValidations.class})
    @Size(min = 20, max = 20, groups = {AddValidations.class})
    private String sku;

    @NotNull(groups = {AddValidations.class})
    @Size(min = 2, max = 50, groups = {AddValidations.class, UpdateValidations.class})
    private String title;


    @NotNull(groups = {AddValidations.class})
    @Size(min = 2, max = 255, groups = {AddValidations.class, UpdateValidations.class})
    private String blurb;

    @NotNull(groups = {AddValidations.class})
    @Positive(groups = {AddValidations.class, UpdateValidations.class})
    private Short pages;

    @NotNull(groups = {AddValidations.class})
    private LocalDate published;

    @NotNull(groups = {AddValidations.class})
    private Integer serieId;

    @NotNull(groups = {AddValidations.class})
    private Integer editorialId;

    @NotNull(groups = {AddValidations.class})
    @NotEmpty(groups = {AddValidations.class, UpdateValidations.class})
    private List<Short> genres;

    @NotNull(groups = {AddValidations.class})
    @NotEmpty(groups = {AddValidations.class, UpdateValidations.class})
    private List<Integer> artists;

    @NotNull(groups = {AddValidations.class})
    @NotEmpty(groups = {AddValidations.class, UpdateValidations.class})
    private List<Integer> writers;

}
