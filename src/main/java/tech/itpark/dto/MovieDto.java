package tech.itpark.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
