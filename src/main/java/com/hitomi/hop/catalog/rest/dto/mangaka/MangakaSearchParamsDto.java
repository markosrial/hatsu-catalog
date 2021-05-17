package com.hitomi.hop.catalog.rest.dto.mangaka;

import com.hitomi.hop.catalog.validation.constraints.IsoCountryCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter @Setter
public class MangakaSearchParamsDto {

    private String name;

    @IsoCountryCode
    private String isoCountry;

    @Min(0)
    private int page = 0;

    @Min(10)
    @Max(100)
    private int size = 10;

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry.trim();
    }

}
