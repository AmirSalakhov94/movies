package tech.itpark.mapper;

import org.mapstruct.Mapper;
import tech.itpark.dto.GenreDto;
import tech.itpark.entity.GenreEntity;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreEntity fromDto(GenreDto genreDto);

    GenreDto fromEntity(GenreEntity genreEntity);

    List<GenreEntity> fromDtos(List<GenreDto> genreDtos);

    List<GenreDto> fromEntities(List<GenreEntity> genreEntities);
}
