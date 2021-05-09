package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.MovieDto;
import tech.itpark.dto.Pageable;
import tech.itpark.dto.PreviewMovieDto;
import tech.itpark.entity.MovieEntity;
import tech.itpark.mapper.MovieMapper;
import tech.itpark.mapper.PreviewMovieMapper;
import tech.itpark.repository.MovieRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final PreviewMovieMapper previewMovieMapper;
    private final MovieMapper movieMapper;
    private final MovieRepository repository;

    @Override
    public List<PreviewMovieDto> topMovies(final int count) {
        return previewMovieMapper.fromEntities(repository.findTopMovies(count));
    }

    @Override
    public List<PreviewMovieDto> getMovies(final Pageable pageable) {
        return previewMovieMapper.fromEntities(repository.findAll(pageable.getOffset(), pageable.getNum()));
    }

    @Override
    public MovieDto getMovie(final UUID uuid) {
        return repository.findByUuid(uuid)
                .map(movieMapper::formEntity)
                .orElse(null);
    }

    @Override
    public List<PreviewMovieDto> topMoviesByGenre(final UUID genreUuid, final int count) {
        return previewMovieMapper.fromEntities(repository.findTopMoviesByGenre(genreUuid, count));
    }

    @Override
    public List<PreviewMovieDto> moviesByCompany(final UUID companyUuid) {
        return previewMovieMapper.fromEntities(repository.moviesByCompany(companyUuid));
    }

    @Override
    public List<UUID> save(List<MovieDto> movies) {
        List<MovieEntity> movieEntities = movieMapper.fromDtos(movies);
        return repository.save(movieEntities, 1000);
    }

    @Override
    public List<PreviewMovieDto> moviesByCollection(UUID collectionUuid) {
        return previewMovieMapper.fromEntities(repository.moviesByCollection(collectionUuid));
    }
}
