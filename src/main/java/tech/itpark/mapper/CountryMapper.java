package tech.itpark.mapper;

import org.mapstruct.Mapper;
import tech.itpark.dto.CountryDto;
import tech.itpark.entity.CountryEntity;

import java.util.List;

@Mapper
public interface CountryMapper {

    CountryEntity fromDto(CountryDto countryDto);

    CountryDto fromEntity(CountryEntity countryEntity);

    List<CountryEntity> fromDtos(List<CountryDto> countryDtos);

    List<CountryDto> fromEntities(List<CountryEntity> countryEntities);
}
