package tech.itpark.mapper;

import org.mapstruct.Mapper;
import tech.itpark.dto.GenreDto;
import tech.itpark.entity.GenreEntity;

import java.util.List;
import java.util.Set;

@Mapper
public interface GenreMapper {

    GenreEntity fromDto(GenreDto genreDto);

    GenreDto fromEntity(GenreEntity genreEntity);

    List<GenreEntity> fromDtos(List<GenreDto> genreDtos);

    List<GenreEntity> fromDtos(Set<GenreDto> genreDtos);

    List<GenreDto> fromEntities(List<GenreEntity> genreEntities);
}
