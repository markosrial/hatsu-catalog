package com.hitomi.hop.catalog.model.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Manga {

    @Id
    private String sku;

    private String title;

    /**
     * Back cover description
     */
    private String blurb;

    private Short pages;

    private LocalDate published;

    private Integer serieId;

    private Integer editorialId;

    @ElementCollection
    @CollectionTable(uniqueConstraints = {@UniqueConstraint(columnNames = {"manga_sku", "genre_id"})})
    @Column(name = "genre_id")
    private List<Short> genres;

    @ElementCollection
    @CollectionTable(uniqueConstraints = {@UniqueConstraint(columnNames = {"manga_sku", "artist_id"})})
    @Column(name = "artist_id")
    private List<Integer> artists;

    @ElementCollection
    @CollectionTable(uniqueConstraints = {@UniqueConstraint(columnNames = {"manga_sku", "writer_id"})})
    @Column(name = "writer_id")
    private List<Integer> writers;

    private String coverImage;

    private String backCoverImage;

    private Boolean removed;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

}
