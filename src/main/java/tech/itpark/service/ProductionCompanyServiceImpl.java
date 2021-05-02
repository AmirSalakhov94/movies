package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.CompanyDto;
import tech.itpark.repository.ProductionCompanyRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProductionCompanyServiceImpl implements ProductionCompanyService {

    private final ProductionCompanyRepository repository;

    @Override
    public List<CompanyDto> getCompanies() {
        return repository.findAll();
    }

    @Override
    public CompanyDto getCompany(long id) {
        return repository.finndById(id);
    }

    @Override
    public void save(Set<CompanyDto> companies) {

    }
}
