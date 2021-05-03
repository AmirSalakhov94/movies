package tech.itpark.service.parser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Service
public class OpenCsvParser implements Parser<CsvParserData> {

    private final Gson gson;

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

        Map<CollectionDto, UUID> collectionMap = new HashMap<>();
        Map<CompanyDto, UUID> companyMap = new HashMap<>();
        Map<CountryDto, UUID> countryMap = new HashMap<>();
        Map<GenreDto, UUID> genreMap = new HashMap<>();
        Map<LanguageDto, UUID> languageMap = new HashMap<>();

        List<CsvDataDto> parseData = csvToBean.parse();
        List<MovieDto> movies = parseData.stream()
                .map(csvData -> {
                    try {
                        MovieDto.MovieDtoBuilder movieDtoBuilder = MovieDto.builder()
                                .uuid(UUID.randomUUID())
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
                                .voteCount(csvData.getVoteCount());

                        Optional.ofNullable(csvData.getCollection())
                                .filter(StringUtils::isNotBlank)
                                .map(v -> gson.fromJson(v.replace("\\", ""), CollectionDto.class))
                                .ifPresent(collection -> {
                                    UUID uuid = collectionMap.get(collection);
                                    if (uuid == null) {
                                        uuid = UUID.randomUUID();
                                        movieDtoBuilder.collection(collection);
                                    }
                                    collection.setUuid(uuid);
                                    collectionMap.put(collection, uuid);
                                });

                        Optional.ofNullable(csvData.getGenres())
                                .filter(StringUtils::isNotBlank)
                                .ifPresent(v -> {
                                    List<GenreDto> currentGenres = Arrays.stream(gson.fromJson(v.replace("\\", ""), GenreDto[].class))
                                            .peek(genre -> {
                                                UUID uuid = genreMap.get(genre);
                                                if (uuid == null) {
                                                    uuid = UUID.randomUUID();
                                                }
                                                genre.setUuid(uuid);
                                                genreMap.put(genre, uuid);
                                            })
                                            .collect(Collectors.toList());
                                    movieDtoBuilder.genres(currentGenres);
                                });

                        Optional.ofNullable(csvData.getCompanies())
                                .filter(StringUtils::isNotBlank)
                                .ifPresent(v -> {
                                    List<CompanyDto> currentCompanies = Arrays.stream(gson.fromJson(v.replace("\\", ""), CompanyDto[].class))
                                            .peek(company -> {
                                                UUID uuid = companyMap.get(company);
                                                if (uuid == null) {
                                                    uuid = UUID.randomUUID();
                                                }
                                                company.setUuid(uuid);
                                                companyMap.put(company, uuid);
                                            })
                                            .collect(Collectors.toList());
                                    movieDtoBuilder.companies(currentCompanies);
                                });

                        Optional.ofNullable(csvData.getCountries())
                                .filter(StringUtils::isNotBlank)
                                .ifPresent(v -> {
                                    List<CountryDto> currentCountries = Arrays.stream(gson.fromJson(v.replace("\\", ""), CountryDto[].class))
                                            .peek(country -> {
                                                UUID uuid = countryMap.get(country);
                                                if (uuid == null) {
                                                    uuid = UUID.randomUUID();
                                                }
                                                country.setUuid(uuid);
                                                countryMap.put(country, uuid);
                                            })
                                            .collect(Collectors.toList());
                                    movieDtoBuilder.countries(currentCountries);
                                });

                        Optional.ofNullable(csvData.getLanguages())
                                .filter(StringUtils::isNotBlank)
                                .ifPresent(v -> {
                                    List<LanguageDto> currentLanguages = Arrays.stream(gson.fromJson(v.replace("\\", ""), LanguageDto[].class))
                                            .peek(language -> {
                                                UUID uuid = languageMap.get(language);
                                                if (uuid == null) {
                                                    uuid = UUID.randomUUID();
                                                }
                                                language.setUuid(uuid);
                                                languageMap.put(language, uuid);
                                            })
                                            .collect(Collectors.toList());
                                    movieDtoBuilder.languages(currentLanguages);
                                });

                        return movieDtoBuilder.build();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());

        return CsvParserData.builder()
                .movies(movies)
                .countries(countryMap.keySet())
                .collections(collectionMap.keySet())
                .companies(companyMap.keySet())
                .genres(genreMap.keySet())
                .languages(languageMap.keySet())
                .capturedExceptions(csvToBean.getCapturedExceptions())
                .build();
    }
}
