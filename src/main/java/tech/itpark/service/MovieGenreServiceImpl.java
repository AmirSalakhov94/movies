package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.GenreDto;
import tech.itpark.repository.MovieGenreRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieGenreServiceImpl implements MovieGenreService {

    private final MovieGenreRepository repository;

    @Override
    public List<GenreDto> getGeneres() {
        return null;
    }

    @Override
    public GenreDto getGenre(UUID uuid) {
        return null;
    }

    @Override
    public void save(Set<GenreDto> genres) {

    }
}
