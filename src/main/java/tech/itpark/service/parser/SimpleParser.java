package tech.itpark.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import tech.itpark.dto.MovieDto;
import tech.itpark.exception.FileNullException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SimpleParser {

    private static final String COMMA_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    private final ObjectMapper objectMapper;

    public List<MovieDto> parse(File file) {
        if (file == null)
            throw new FileNullException("file is null");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .skip(1)
                    .map(data -> {
                        try {
                            System.out.println(data);
                            return parseLine(data.split(COMMA_DELIMITER));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private MovieDto parseLine(final String[] splitData) throws JsonProcessingException {

//        Gson gson = new Gson();
//        final MovieDto.MovieDtoBuilder builder = MovieDto.builder();
//        int index = 0;
//        for (String data : splitData) {
//            if (data == null || data.isEmpty()) {
//                continue;
//            }
//            switch (index) {
//                case 0 -> builder.budget(Long.parseLong(data));
//                case 1 -> {
//                    List<GenreDto> genres = Arrays.asList(objectMapper.readValue(simpleData(data), GenreDto[].class));
//                    builder.genres(genres);
//                }
//                case 2 -> builder.homepage(data);
//                case 3 -> builder.idWithFile(Long.parseLong(data));
//                case 4 -> {
//                    List<KeywordDto> keywords = Arrays.asList(objectMapper.readValue(simpleData(data), KeywordDto[].class));
//                    builder.keywords(keywords);
//                }
//                case 5 -> builder.originalLanguage(data);
//                case 6 -> builder.originalTitle(data);
//                case 7 -> builder.overview(simpleData(data));
//                case 8 -> builder.popularity(Float.parseFloat(data));
//                case 9 -> {
//                    List<CompanyDto> companies = Arrays.asList(objectMapper.readValue(simpleData(data), CompanyDto[].class));
//                    builder.companies(companies);
//                }
//                case 10 -> {
//                    List<CountryDto> countries = Arrays.asList(objectMapper.readValue(simpleData(data), CountryDto[].class));
//                    builder.countries(countries);
//                }
//                case 11 -> {
//                    LocalDate date = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
//                    builder.releaseDate(date);
//                }
//                case 12 -> builder.revenue(Long.parseLong(data));
//                case 13 -> builder.runtime(Short.parseShort(data));
//                case 14 -> {
//                    List<LanguageDto> languages = Arrays.asList(objectMapper.readValue(simpleData(data), LanguageDto[].class));
//                    builder.languages(languages);
//                }
//                case 15 -> builder.status(Status.valueOf(data.toUpperCase()));
//                case 16 -> builder.tagline(data);
//                case 17 -> builder.title(data);
//                case 18 -> builder.voteAverage(Float.parseFloat(data));
//                case 19 -> builder.voteCount(Long.parseLong(data));
//                default -> System.out.println("Not found, switch: " + index);
//            }
//            index++;
//        }
//
//        return builder.build();
        return null;
    }
}
