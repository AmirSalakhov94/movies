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
    public List<PreviewMovieDto> movies(Pageable pageable) {
        return movieService.getMovies(pageable);
    }

    @GetMapping("/{id}")
    public MovieDto movie(@PathVariable("id") long id) {
        return movieService.getMovie(id);
    }

    @GetMapping("/genre/{genreId}")
    public List<PreviewMovieDto> topMoviesByGenre(@PathVariable("genreId") long genreId) {
        return movieService.topMoviesByGenre(genreId);
    }

    @GetMapping("/company/{companyId}")
    public List<PreviewMovieDto> moviesByCompany(@PathVariable("companyId") long companyId) {
        return movieService.moviesByCompany(companyId);
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("multipartFile") MultipartFile multipartFile) {
        fileUploaderService.upload(multipartFile);
    }
}
