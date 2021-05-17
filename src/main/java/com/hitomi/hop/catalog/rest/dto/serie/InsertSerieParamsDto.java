package com.hitomi.hop.catalog.rest.dto.serie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter @Setter
public class InsertSerieParamsDto {

    public interface AddValidations {}
    public interface UpdateValidations {}

    @NotNull(groups = {AddValidations.class})
    @Size(min=1, max=30, groups = {AddValidations.class, UpdateValidations.class})
    private String name;

    @NotNull(groups = {AddValidations.class})
    @Size(min=10, max=255, groups = {AddValidations.class, UpdateValidations.class})
    private String description;

}
