package tech.itpark.service;

import tech.itpark.dto.CompanyDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductionCompanyService {

    List<CompanyDto> getCompanies();

    CompanyDto getCompany(final UUID uuid);

    void save(Set<CompanyDto> companies);
}
