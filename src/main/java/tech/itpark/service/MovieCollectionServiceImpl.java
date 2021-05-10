package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tech.itpark.dto.CollectionDto;
import tech.itpark.exception.CollectionNotFoundException;
import tech.itpark.mapper.CollectionMapper;
import tech.itpark.repository.MovieCollectionRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieCollectionServiceImpl implements MovieCollectionService {

    public static final int BATCH_SIZE = 3000;
    private final CollectionMapper mapper;
    private final MovieCollectionRepository repository;

    @Override
    public List<CollectionDto> getCollections() {
        return mapper.fromEntities(repository.findAll());
    }

    @Override
    public CollectionDto getCollection(final UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::fromEntity)
                .orElseThrow(() -> new CollectionNotFoundException("not found collection by uuid: " + uuid));
    }

    @Async
    @Override
    public List<UUID> save(final Set<CollectionDto> collections) {
        return repository.save(mapper.fromDtos(collections), BATCH_SIZE);
    }
}
