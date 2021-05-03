package tech.itpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.itpark.dto.enums.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

    private UUID uuid;
    private boolean adult;
    private long idWithFile;
    private String imdbId;
    private long budget;
    private CollectionEntity collection;
    private List<GenreEntity> genres;
    private String homepage;
    private String originalLanguage;
    private String originalTitle;
    private String posterPath;
    private String overview;
    private float popularity;
    private List<CompanyEntity> companies;
    private List<CountryEntity> countries;
    private LocalDate releaseDate;
    private long revenue;
    private float runtime;
    private List<LanguageEntity> languages;
    private Status status;
    private String tagline;
    private String title;
    private boolean video;
    private float voteAverage;
    private long voteCount;
}
