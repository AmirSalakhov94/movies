package tech.itpark.service;

import tech.itpark.dto.CompanyDto;

import java.util.List;
import java.util.Set;

public interface ProductionCompanyService {

    List<CompanyDto> getCompanies();

    CompanyDto getCompany(final long id);

    void save(Set<CompanyDto> companies);
}
