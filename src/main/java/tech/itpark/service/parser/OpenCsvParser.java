package tech.itpark.service.parser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tech.itpark.dto.*;
import tech.itpark.dto.enums.Status;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OpenCsvParser implements Parser<CsvParserData> {

    private final Gson gson = new Gson();

    @Override
    public CsvParserData parse(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            parse(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return CsvParserData.builder().build();
    }

    @Override
    public CsvParserData parse(InputStreamReader inputStreamReader) {
        CsvToBean<CsvDataDto> csvToBean = new CsvToBeanBuilder(inputStreamReader)
                .withType(CsvDataDto.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withThrowExceptions(false)
                .build();

        Set<CollectionDto> collections = new HashSet<>();
        Set<CompanyDto> companies = new HashSet<>();
        Set<CountryDto> countries = new HashSet<>();
        Set<GenreDto> genres = new HashSet<>();
        Set<LanguageDto> languages = new HashSet<>();
        List<CsvDataDto> parseData = csvToBean.parse();
        List<MovieDto> movies = parseData.stream()
                .map(csvData -> {
                    try {
                        MovieDto movie = MovieDto.builder()
                                .idWithFile(csvData.getId())
                                .adult(csvData.isAdult())
                                .imdbId(csvData.getImdbId())
                                .budget(csvData.getBudget())
                                .homepage(csvData.getHomepage())
                                .originalTitle(csvData.getOriginalTitle())
                                .originalLanguage(csvData.getOriginalLanguage())
                                .popularity(csvData.getPopularity())
                                .posterPath(csvData.getPosterPath())
                                .overview(csvData.getOverview())
                                .releaseDate(StringUtils.isNotBlank(csvData.getReleaseDate())
                                        ? LocalDate.parse(csvData.getReleaseDate(), DateTimeFormatter.ISO_LOCAL_DATE) : null)
                                .revenue(csvData.getRevenue())
                                .runtime(csvData.getRuntime())
                                .status(Status.getStatus(csvData.getStatus()))
                                .tagline(csvData.getTagline())
                                .title(csvData.getTitle())
                                .video(csvData.isVideo())
                                .voteAverage(csvData.getVoteAverage())
                                .voteCount(csvData.getVoteCount())
                                .collection(StringUtils.isNotBlank(csvData.getCollections())
                                        ? gson.fromJson(csvData.getCollections().replace("\\", ""), CollectionDto.class) : null)
                                .genres(StringUtils.isNotBlank(csvData.getGenres()) ?
                                        Arrays.asList(gson.fromJson(csvData.getGenres().replace("\\", ""), GenreDto[].class)) : null)
                                .companies(StringUtils.isNotBlank(csvData.getCompanies()) ?
                                        Arrays.asList(gson.fromJson(csvData.getCompanies().replace("\\", ""), CompanyDto[].class)) : null)
                                .countries(StringUtils.isNotBlank(csvData.getCountries()) ?
                                        Arrays.asList(gson.fromJson(csvData.getCountries().replace("\\", ""), CountryDto[].class)) : null)
                                .languages(StringUtils.isNotBlank(csvData.getLanguages()) ?
                                        Arrays.asList(gson.fromJson(csvData.getLanguages().replace("\\", ""), LanguageDto[].class)) : null)
                                .build();

                        Optional.ofNullable(movie.getCollection())
                                .ifPresent(collections::add);
                        Optional.ofNullable(movie.getCompanies())
                                .ifPresent(companies::addAll);
                        Optional.ofNullable(movie.getCountries())
                                .ifPresent(countries::addAll);
                        Optional.ofNullable(movie.getGenres())
                                .ifPresent(genres::addAll);
                        Optional.ofNullable(movie.getLanguages())
                                .ifPresent(languages::addAll);

                        return movie;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());

        return CsvParserData.builder()
                .movies(movies)
                .collections(collections)
                .companies(companies)
                .genres(genres)
                .languages(languages)
                .capturedExceptions(csvToBean.getCapturedExceptions())
                .build();
    }
}
