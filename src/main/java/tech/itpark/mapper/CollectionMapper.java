package tech.itpark.mapper;

import org.mapstruct.Mapper;
import tech.itpark.dto.CollectionDto;
import tech.itpark.entity.CollectionEntity;

import java.util.List;
import java.util.Set;

@Mapper
public interface CollectionMapper {

    CollectionEntity fromDto(CollectionDto collectionDto);

    CollectionDto fromEntity(CollectionEntity collectionEntities);

    List<CollectionEntity> fromDtos(List<CollectionDto> collectionDtos);

    List<CollectionEntity> fromDtos(Set<CollectionDto> collectionDtos);

    List<CollectionDto> fromEntities(List<CollectionEntity> collectionEntities);
}
