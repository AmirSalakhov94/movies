package tech.itpark.dto;

import lombok.*;
import tech.itpark.dto.enums.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    @EqualsAndHashCode.Exclude
    private UUID uuid;
    private Boolean adult;
    private Long idWithFile;
    private String imdbId;
    private Long budget;
    private CollectionDto collection;
    private List<GenreDto> genres;
    private String homepage;
    private String originalLanguage;
    private String originalTitle;
    private String posterPath;
    private String overview;
    private Float popularity;
    private List<CompanyDto> companies;
    private List<CountryDto> countries;
    private LocalDate releaseDate;
    private Long revenue;
    private Float runtime;
    private List<LanguageDto> languages;
    private Status status;
    private String tagline;
    private String title;
    private Boolean video;
    private Float voteAverage;
    private Long voteCount;
}
