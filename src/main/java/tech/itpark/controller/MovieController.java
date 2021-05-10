package tech.itpark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.dto.MovieDto;
import tech.itpark.dto.Pageable;
import tech.itpark.dto.PreviewMovieDto;
import tech.itpark.service.FileUploaderService;
import tech.itpark.service.MovieService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final FileUploaderService fileUploaderService;

    @GetMapping("/top")
    public List<PreviewMovieDto> topPopularMovies(@RequestParam(name = "count", defaultValue = "20") final int count) {
        return movieService.topMovies(count);
    }

    @GetMapping("/all")
    public List<PreviewMovieDto> movies(@ModelAttribute("pageable") final Pageable pageable) {
        return movieService.getMovies(pageable);
    }

    @GetMapping("/{uuid}")
    public MovieDto movie(@PathVariable("uuid") final UUID uuid) {
        return movieService.getMovie(uuid);
    }

    @GetMapping("/collection/{collectionUuid}")
    public List<PreviewMovieDto> moviesByCollection(@PathVariable("collectionUuid") final UUID collectionUuid) {
        return movieService.moviesByCollection(collectionUuid);
    }

    @GetMapping("/genre/{genreUuid}")
    public List<PreviewMovieDto> topMoviesByGenre(@PathVariable("genreUuid") final UUID genreUuid) {
        return movieService.topMoviesByGenre(genreUuid, 20);
    }

    @GetMapping("/company/{companyUuid}")
    public List<PreviewMovieDto> moviesByCompany(@PathVariable("companyUuid") final UUID companyUuid) {
        return movieService.moviesByCompany(companyUuid);
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("multipartFile") final MultipartFile multipartFile) {
        fileUploaderService.upload(multipartFile);
    }
}
