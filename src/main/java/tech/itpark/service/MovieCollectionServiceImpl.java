package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.CollectionDto;
import tech.itpark.repository.MovieCollectionRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MovieCollectionServiceImpl implements MovieCollectionService {

    private final MovieCollectionRepository repository;

    @Override
    public List<CollectionDto> getCollections() {
        return null;
    }

    @Override
    public CollectionDto getCollection(long id) {
        return null;
    }

    @Override
    public Set<Long> save(Set<CollectionDto> collections) {
        return null;
    }
}