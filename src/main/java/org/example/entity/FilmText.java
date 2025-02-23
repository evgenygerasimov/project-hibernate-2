package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "film_text")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;
}
