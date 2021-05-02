package tech.itpark.service;

import tech.itpark.dto.CollectionDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MovieCollectionService {

    List<CollectionDto> getCollections();

    CollectionDto getCollection(final UUID uuid);

    Set<Long> save(Set<CollectionDto> collections);
}
