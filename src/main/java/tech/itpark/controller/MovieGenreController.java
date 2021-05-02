package tech.itpark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.itpark.dto.GenreDto;
import tech.itpark.service.MovieGenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genre")
public class MovieGenreController {

    private final MovieGenreService movieGenreService;

    @GetMapping("/all")
    public List<GenreDto> genres() {
        return movieGenreService.getGeneres();
    }

    @GetMapping("/{id}")
    public GenreDto genre(@PathVariable("id") long id) {
        return movieGenreService.getGenre(id);
    }
}