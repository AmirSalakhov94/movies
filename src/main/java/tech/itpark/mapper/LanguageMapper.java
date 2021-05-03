package tech.itpark.mapper;

import org.mapstruct.Mapper;
import tech.itpark.dto.LanguageDto;
import tech.itpark.entity.LanguageEntity;

import java.util.List;
import java.util.Set;

@Mapper
public interface LanguageMapper {

    LanguageEntity fromDto(LanguageDto languageDto);

    LanguageDto fromEntity(LanguageEntity languageEntity);

    List<LanguageEntity> fromDtos(List<LanguageDto> languageDtos);

    List<LanguageEntity> fromDtos(Set<LanguageDto> languageDtos);

    List<LanguageDto> fromEntities(List<LanguageEntity> languageEntities);
}
