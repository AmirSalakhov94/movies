package tech.itpark.service;

import tech.itpark.dto.MovieDto;
import tech.itpark.dto.Pageable;
import tech.itpark.dto.PreviewMovieDto;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    List<PreviewMovieDto> topMovies(final int count);

    List<PreviewMovieDto> getMovies(final Pageable pageable);

    MovieDto getMovie(final UUID uuid);

    List<PreviewMovieDto> topMoviesByGenre(final UUID genreUuid, final int count);

    List<PreviewMovieDto> moviesByCompany(final UUID companyUuid);

    List<UUID> save(final List<MovieDto> movies);
}
