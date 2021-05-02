package tech.itpark.repository;

import org.springframework.stereotype.Repository;
import tech.itpark.dto.CompanyDto;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductionCompanyRepository {

    List<CompanyDto> findAll();

    CompanyDto finndByUuid(UUID uuid);
}
