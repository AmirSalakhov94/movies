package tech.itpark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.itpark.dto.enums.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private UUID id;
    private boolean adult;
    private long idWithFile;
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
