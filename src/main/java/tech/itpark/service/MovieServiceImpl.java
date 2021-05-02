package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.MovieDto;
import tech.itpark.dto.Pageable;
import tech.itpark.dto.PreviewMovieDto;
import tech.itpark.entity.MovieEntity;
import tech.itpark.mapper.MovieMapper;
import tech.itpark.repository.MovieRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieMapper mapper;
    private final MovieRepository repository;

    @Override
    public List<PreviewMovieDto> topMovies(final int count) {
        return null;
    }

    @Override
    public List<PreviewMovieDto> getMovies(final Pageable pageable) {
        return null;
    }

    @Override
    public MovieDto getMovie(final UUID uuid) {
        return null;
    }

    @Override
    public List<PreviewMovieDto> topMoviesByGenre(final UUID genreUuid) {
        return null;
    }

    @Override
    public List<PreviewMovieDto> moviesByCompany(final UUID companyUuid) {
        return null;
    }

    @Override
    public List<UUID> save(List<MovieDto> movies) {
        List<MovieEntity> movieEntities = mapper.fromDtos(movies);
        return repository.save(movieEntities, 500);
    }
}
