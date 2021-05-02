package tech.itpark.service;

import tech.itpark.dto.CountryDto;

import java.util.List;
import java.util.Set;

public interface MovieCountryService {

    List<CountryDto> getCountries();

    CountryDto getCountry(final long id);

    void save(Set<CountryDto> countries);
}
