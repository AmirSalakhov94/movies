package tech.itpark.dto;

import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvParserData {

    private List<MovieDto> movies;
    private Set<CollectionDto> collections;
    private Set<CompanyDto> companies;
    private Set<CountryDto> countries;
    private Set<GenreDto> genres;
    private Set<LanguageDto> languages;
    private List<CsvException> capturedExceptions;
}
