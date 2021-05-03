package tech.itpark.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.entity.CountryEntity;

import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MovieCountryRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<UUID> save(final List<CountryEntity> countries, final int batchSize) {
        if (countries == null || countries.isEmpty())
            return Collections.emptyList();

        jdbcTemplate.batchUpdate("INSERT INTO countries (uuid, iso_3166_1, name) values (?, ?, ?)",
                countries, batchSize, (preparedStatement, countryEntity) -> {
                    int index = 0;
                    preparedStatement.setObject(++index, countryEntity.getUuid(), Types.OTHER);
                    preparedStatement.setString(++index, countryEntity.getIso31661());
                    preparedStatement.setString(++index, countryEntity.getName());
                });
        return countries.stream().map(CountryEntity::getUuid).collect(Collectors.toList());
    }

    public List<CountryEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM countries",
                (rs, i) -> {
                    CountryEntity country = new CountryEntity();
                    country.setUuid(rs.getObject("uuid", UUID.class));
                    country.setIso31661(rs.getString("iso_3166_1"));
                    country.setName(rs.getString("name"));
                    return country;
                });
    }

    public Optional<CountryEntity> findByUuid(final UUID uuid) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM countries WHERE uuid = ?",
                (rs, i) -> {
                    CountryEntity country = new CountryEntity();
                    country.setUuid(rs.getObject("uuid", UUID.class));
                    country.setIso31661(rs.getString("iso_3166_1"));
                    country.setName(rs.getString("name"));
                    return country;
                }, uuid));
    }
}
