package tech.itpark.service;

import tech.itpark.dto.MovieDto;
import tech.itpark.dto.Pageable;
import tech.itpark.dto.PreviewMovieDto;

import java.util.List;

public interface MovieService {

    List<PreviewMovieDto> topMovies(final int count);

    List<PreviewMovieDto> getMovies(final Pageable pageable);

    MovieDto getMovie(final long id);

    List<PreviewMovieDto> topMoviesByGenre(final long genreId);

    List<PreviewMovieDto> moviesByCompany(final long companyId);

    void save(final List<MovieDto> movies);
}
