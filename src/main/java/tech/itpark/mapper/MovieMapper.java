package tech.itpark.mapper;

import org.mapstruct.Mapper;
import tech.itpark.dto.MovieDto;
import tech.itpark.entity.MovieEntity;

import java.util.List;

@Mapper
public interface MovieMapper {

    MovieEntity fromDto(MovieDto movieDto);

    MovieDto formEntity(MovieEntity movieEntity);

    List<MovieEntity> fromDtos(List<MovieDto> movieDtos);

    List<MovieDto> fromEntities(List<MovieEntity> movieEntities);
}
