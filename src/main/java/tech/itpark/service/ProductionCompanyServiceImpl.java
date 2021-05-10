package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.CompanyDto;
import tech.itpark.exception.ProductionCompanyNotFoundException;
import tech.itpark.mapper.CompanyMapper;
import tech.itpark.repository.ProductionCompanyRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductionCompanyServiceImpl implements ProductionCompanyService {

    public static final int BATCH_SIZE = 3000;
    private final CompanyMapper mapper;
    private final ProductionCompanyRepository repository;

    @Override
    public List<CompanyDto> getCompanies() {
        return mapper.fromEntities(repository.findAll());
    }

    @Override
    public CompanyDto getCompany(final UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::fromEntity)
                .orElseThrow(() -> new ProductionCompanyNotFoundException("not found production company by uuid: " + uuid));
    }

    @Override
    public void save(final Set<CompanyDto> companies) {
        repository.save(mapper.fromDtos(companies), BATCH_SIZE);
    }
}
