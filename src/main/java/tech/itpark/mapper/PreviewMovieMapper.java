package tech.itpark.mapper;

import org.mapstruct.Mapper;
import tech.itpark.dto.PreviewMovieDto;
import tech.itpark.entity.PreviewMovieEntity;

import java.util.List;

@Mapper
public interface PreviewMovieMapper {

    PreviewMovieDto fromEntity(PreviewMovieEntity previewMovie);

    List<PreviewMovieDto> fromEntities(List<PreviewMovieEntity> previewMovieEntities);

    List<PreviewMovieEntity> fromDtos(List<PreviewMovieDto> previewMovieDtos);

    PreviewMovieEntity fromDtos(PreviewMovieDto previewMovie);
}
