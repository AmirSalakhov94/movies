package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.MovieDto;
import tech.itpark.dto.Pageable;
import tech.itpark.dto.PreviewMovieDto;
import tech.itpark.repository.MovieRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<PreviewMovieDto> topMovies(final int count) {
        return null;
    }

    @Override
    public List<PreviewMovieDto> getMovies(final Pageable pageable) {
        return null;
    }

    @Override
    public MovieDto getMovie(final long id) {
        return null;
    }

    @Override
    public List<PreviewMovieDto> topMoviesByGenre(final long genreId) {
        return null;
    }

    @Override
    public List<PreviewMovieDto> moviesByCompany(final long companyId) {
        return null;
    }

    @Override
    public void save(List<MovieDto> movies) {

    }
}
