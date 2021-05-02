package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.dto.LanguageDto;
import tech.itpark.repository.MovieLanguageRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MovieLanguageServiceImpl implements MovieLanguageService {

    private final MovieLanguageRepository repository;

    @Override
    public List<LanguageDto> getLanguages() {
        return null;
    }

    @Override
    public LanguageDto getLanguage(long id) {
        return null;
    }

    @Override
    public void save(Set<LanguageDto> languages) {

    }
}
