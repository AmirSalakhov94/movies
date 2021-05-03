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
    public List<PreviewMovieDto> topPopularMovies(@RequestParam(name = "count", defaultValue = "20") int count) {
        return movieService.topMovies(count);
    }

    @GetMapping("/all")
    public List<PreviewMovieDto> movies(@ModelAttribute("pageable") Pageable pageable) {
        return movieService.getMovies(pageable);
    }

    @GetMapping("/{uuid}")
    public MovieDto movie(@PathVariable("uuid") UUID uuid) {
        return movieService.getMovie(uuid);
    }

    @GetMapping("/genre/{genreUuid}")
    public List<PreviewMovieDto> topMoviesByGenre(@PathVariable("genreUuid") UUID genreUuid) {
        return movieService.topMoviesByGenre(genreUuid, 20);
    }

    @GetMapping("/company/{companyUuid}")
    public List<PreviewMovieDto> moviesByCompany(@PathVariable("companyUuid") UUID companyUuid) {
        return movieService.moviesByCompany(companyUuid);
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("multipartFile") MultipartFile multipartFile) {
        fileUploaderService.upload(multipartFile);
    }
}
