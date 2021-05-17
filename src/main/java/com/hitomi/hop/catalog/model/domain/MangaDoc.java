package com.hitomi.hop.catalog.model.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "mangas")
@Setter @Getter
public class MangaDoc {

    @Id
    private String sku;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String blurb;

    @Field(type = FieldType.Short)
    private short pages;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate published;

    @Field(type = FieldType.Integer)
    private Integer serieId;

    @Field(type = FieldType.Integer)
    private Integer editorialId;

    @Field(type = FieldType.Short)
    private List<Short> genres;

    @Field(type = FieldType.Integer)
    private List<Integer> artists;

    @Field(type = FieldType.Integer)
    private List<Integer> writers;

    @Field(type = FieldType.Keyword)
    private String coverImage;

    @Field(type = FieldType.Keyword)
    private String backCoverImage;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private LocalDateTime created;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private LocalDateTime updated;

    @Field(type = FieldType.Boolean)
    private Boolean removed;

}
