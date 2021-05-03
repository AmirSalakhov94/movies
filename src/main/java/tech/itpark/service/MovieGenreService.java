package tech.itpark.service;

import tech.itpark.dto.GenreDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MovieGenreService {

    List<GenreDto> getGeneres();

    GenreDto getGenre(final UUID uuid);

    List<UUID> save(final Set<GenreDto> genres);
}
