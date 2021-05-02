package tech.itpark.repository;

import org.springframework.stereotype.Repository;
import tech.itpark.dto.CompanyDto;

import java.util.List;

@Repository
public interface ProductionCompanyRepository {

    List<CompanyDto> findAll();

    CompanyDto finndById(long id);
}
