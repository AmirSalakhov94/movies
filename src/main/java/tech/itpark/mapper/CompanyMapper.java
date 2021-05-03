package tech.itpark.mapper;

import org.mapstruct.Mapper;
import tech.itpark.dto.CompanyDto;
import tech.itpark.entity.CompanyEntity;

import java.util.List;
import java.util.Set;

@Mapper
public interface CompanyMapper {

    CompanyEntity fromDto(CompanyDto companyDto);

    CompanyDto fromEntity(CompanyEntity companyEntity);

    List<CompanyEntity> fromDtos(List<CompanyDto> companyDtos);

    List<CompanyEntity> fromDtos(Set<CompanyDto> companyDtos);

    List<CompanyDto> fromEntities(List<CompanyEntity> companyEntities);
}
