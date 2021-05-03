package tech.itpark.service;

import tech.itpark.dto.LanguageDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MovieLanguageService {

    List<LanguageDto> getLanguages();

    LanguageDto getLanguage(final UUID uuid);

    List<UUID> save(final Set<LanguageDto> languages);
}
