package tech.itpark.service;

import tech.itpark.dto.CountryDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MovieCountryService {

    List<CountryDto> getCountries();

    CountryDto getCountry(final UUID uuid);

    List<UUID> save(final Set<CountryDto> countries);
}
