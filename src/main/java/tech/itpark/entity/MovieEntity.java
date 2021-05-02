package tech.itpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.itpark.dto.*;
import tech.itpark.dto.enums.Status;
import tech.itpark.jdbc.annotation.Column;
import tech.itpark.jdbc.annotation.Entity;
import tech.itpark.jdbc.annotation.Table;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table("movies")
public class MovieEntity {

    private boolean adult;
    @Column("id_with_file")
    private long idWithFile;
    @Column("imdb_id")
    private String imdbId;
    private long budget;
    private CollectionDto collection;
    private List<GenreDto> genres;
    private String homepage;
    private String originalLanguage;
    private String originalTitle;
    private String posterPath;
    private String overview;
    private float popularity;
    private List<CompanyDto> companies;
    private List<CountryDto> countries;
    private LocalDate releaseDate;
    private long revenue;
    private float runtime;
    private List<LanguageDto> languages;
    private Status status;
    private String tagline;
    private String title;
    private boolean video;
    private float voteAverage;
    private long voteCount;
}
