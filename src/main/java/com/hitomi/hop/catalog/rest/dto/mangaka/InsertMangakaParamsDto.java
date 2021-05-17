package com.hitomi.hop.catalog.rest.dto.mangaka;

import com.hitomi.hop.catalog.validation.constraints.IsoCountryCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
public class InsertMangakaParamsDto {

    public interface AddValidations {}
    public interface UpdateValidations {}

    @NotNull(groups = {AddValidations.class})
    @Size(min = 2, max = 70, groups = {AddValidations.class, UpdateValidations.class})
    private String name;

    @NotNull(groups = {AddValidations.class})
    private LocalDate birthDate;

    @NotNull(groups = {AddValidations.class})
    @IsoCountryCode(groups = {AddValidations.class, UpdateValidations.class})
    private String isoCountry;

    // Setters

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry.trim();
    }

}
