package tech.itpark.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.entity.GenreEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MovieGenreRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<UUID> save(final List<GenreEntity> genres, final int batchSize) {
        if (genres == null || genres.isEmpty())
            return Collections.emptyList();

        jdbcTemplate.batchUpdate("INSERT INTO genres (uuid, id_with_file, name) values (?, ?, ?)",
                genres, batchSize, (preparedStatement, genreEntity) -> {
                    int index = 0;
                    preparedStatement.setObject(++index, genreEntity.getUuid(), Types.OTHER);
                    preparedStatement.setLong(++index, genreEntity.getIdWithFile());
                    preparedStatement.setString(++index, genreEntity.getName());
                });
        return genres.stream().map(GenreEntity::getUuid).collect(Collectors.toList());
    }

    public List<GenreEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM genres", this::fillGenreByResultSet);
    }

    public Optional<GenreEntity> findByUuid(final UUID uuid) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM genres WHERE uuid = ?",
                    this::fillGenreByResultSet, uuid));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    private GenreEntity fillGenreByResultSet(ResultSet rs, int i) throws SQLException {
        GenreEntity genre = new GenreEntity();
        genre.setUuid(rs.getObject("uuid", UUID.class));
        genre.setIdWithFile(rs.getLong("id_with_file"));
        genre.setName(rs.getString("name"));
        return genre;
    }
}
