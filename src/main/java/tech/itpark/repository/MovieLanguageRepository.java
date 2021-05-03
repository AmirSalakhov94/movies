package tech.itpark.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.entity.LanguageEntity;

import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MovieLanguageRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<UUID> save(final List<LanguageEntity> languages, final int batchSize) {
        if (languages == null || languages.isEmpty())
            return Collections.emptyList();

        jdbcTemplate.batchUpdate("INSERT INTO languages (uuid, iso_639_1, name) values (?, ?, ?)",
                languages, batchSize, (preparedStatement, languageEntity) -> {
                    int index = 0;
                    preparedStatement.setObject(++index, languageEntity.getUuid(), Types.OTHER);
                    preparedStatement.setString(++index, languageEntity.getIso6391());
                    preparedStatement.setString(++index, languageEntity.getName());
                });
        return languages.stream().map(LanguageEntity::getUuid).collect(Collectors.toList());
    }

    public List<LanguageEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM languages",
                (rs, i) -> {
                    LanguageEntity language = new LanguageEntity();
                    language.setUuid(rs.getObject("uuid", UUID.class));
                    language.setIso6391(rs.getString("iso_639_1"));
                    language.setName(rs.getString("name"));
                    return language;
                });
    }

    public Optional<LanguageEntity> findByUuid(final UUID uuid) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM languages WHERE uuid = ?",
                (rs, i) -> {
                    LanguageEntity language = new LanguageEntity();
                    language.setUuid(rs.getObject("uuid", UUID.class));
                    language.setIso6391(rs.getString("iso_639_1"));
                    language.setName(rs.getString("name"));
                    return language;
                }, uuid));
    }
}
