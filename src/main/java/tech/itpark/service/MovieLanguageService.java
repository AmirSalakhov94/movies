package tech.itpark.service;

import tech.itpark.dto.LanguageDto;

import java.util.List;
import java.util.Set;

public interface MovieLanguageService {

    List<LanguageDto> getLanguages();

    LanguageDto getLanguage(final long id);

    void save(Set<LanguageDto> languages);
}
