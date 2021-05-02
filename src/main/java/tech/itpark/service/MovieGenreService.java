package tech.itpark.service;

import tech.itpark.dto.GenreDto;

import java.util.List;
import java.util.Set;

public interface MovieGenreService {

    List<GenreDto> getGeneres();

    GenreDto getGenre(final long id);

    void save(Set<GenreDto> genres);
}
