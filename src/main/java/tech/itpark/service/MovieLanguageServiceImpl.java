package tech.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tech.itpark.dto.LanguageDto;
import tech.itpark.exception.LanguageNotFoundException;
import tech.itpark.mapper.LanguageMapper;
import tech.itpark.repository.MovieLanguageRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieLanguageServiceImpl implements MovieLanguageService {

    public static final int BATCH_SIZE = 3000;
    private final LanguageMapper mapper;
    private final MovieLanguageRepository repository;

    @Override
    public List<LanguageDto> getLanguages() {
        return mapper.fromEntities(repository.findAll());
    }

    @Override
    public LanguageDto getLanguage(final UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::fromEntity)
                .orElseThrow(() -> new LanguageNotFoundException("not found language by uuid: " + uuid));
    }

    @Async
    @Override
    public List<UUID> save(Set<LanguageDto> languages) {
        return repository.save(mapper.fromDtos(languages), BATCH_SIZE);
    }
}
