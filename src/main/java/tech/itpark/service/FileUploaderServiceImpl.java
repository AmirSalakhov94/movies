package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.dto.CsvParserData;
import tech.itpark.service.parser.Parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class FileUploaderServiceImpl implements FileUploaderService {

    private final Parser<CsvParserData> parser;
    private final MovieCollectionService movieCollectionService;
    private final MovieGenreService movieGenreService;
    private final ProductionCompanyService productionCompanyService;
    private final MovieLanguageService movieLanguageService;
    private final MovieCountryService movieCountryService;
    private final MovieService movieService;

    @Override
    public void upload(final MultipartFile multipartFile) {
        try {
            CsvParserData parse = parser.parse(new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8));
            movieCollectionService.save(parse.getCollections());
            movieCountryService.save(parse.getCountries());
            movieGenreService.save(parse.getGenres());
            movieLanguageService.save(parse.getLanguages());
            productionCompanyService.save(parse.getCompanies());
            movieService.save(parse.getMovies());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
