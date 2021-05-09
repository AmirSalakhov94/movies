package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tech.itpark.dto.CountryDto;
import tech.itpark.mapper.CountryMapper;
import tech.itpark.repository.MovieCountryRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieCountryServiceImpl implements MovieCountryService {

    private final CountryMapper mapper;
    private final MovieCountryRepository repository;

    @Override
    public List<CountryDto> getCountries() {
        return mapper.fromEntities(repository.findAll());
    }

    @Override
    public CountryDto getCountry(final UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::fromEntity)
                .orElse(null);
    }

    @Async
    @Override
    public List<UUID> save(final Set<CountryDto> countries) {
        return repository.save(mapper.fromDtos(countries), 3000);
    }
}
