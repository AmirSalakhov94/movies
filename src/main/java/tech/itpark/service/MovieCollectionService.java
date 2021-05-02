package tech.itpark.service;

import tech.itpark.dto.CollectionDto;

import java.util.List;
import java.util.Set;

public interface MovieCollectionService {

    List<CollectionDto> getCollections();

    CollectionDto getCollection(final long id);

    Set<Long> save(Set<CollectionDto> collections);
}
