package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.CountryDto;
import tech.itpark.repository.MovieCountryRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MovieCountryServiceImpl implements MovieCountryService {

    private final MovieCountryRepository repository;

    @Override
    public List<CountryDto> getCountries() {
        return null;
    }

    @Override
    public CountryDto getCountry(long id) {
        return null;
    }

    @Override
    public void save(Set<CountryDto> countries) {

    }
}
