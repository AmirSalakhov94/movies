package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tech.itpark.dto.GenreDto;
import tech.itpark.mapper.GenreMapper;
import tech.itpark.repository.MovieGenreRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieGenreServiceImpl implements MovieGenreService {

    private final GenreMapper mapper;
    private final MovieGenreRepository repository;

    @Override
    public List<GenreDto> getGeneres() {
        return mapper.fromEntities(repository.findAll());
    }

    @Override
    public GenreDto getGenre(final UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::fromEntity)
                .orElse(null);
    }

    @Async
    @Override
    public List<UUID> save(final Set<GenreDto> genres) {
        return repository.save(mapper.fromDtos(genres), 3000);
    }
}
