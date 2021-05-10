package tech.itpark.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.entity.CollectionEntity;

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
public class MovieCollectionRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<UUID> save(final List<CollectionEntity> collections, final int batchSize) {
        if (collections == null || collections.isEmpty())
            return Collections.emptyList();

        jdbcTemplate.batchUpdate("INSERT INTO collections (uuid, id_with_file, name, poster_path, backdrop_path) values (?, ?, ?, ?, ?)",
                collections, batchSize, (preparedStatement, collectionEntity) -> {
                    int index = 0;
                    preparedStatement.setObject(++index, collectionEntity.getUuid(), Types.OTHER);
                    preparedStatement.setLong(++index, collectionEntity.getIdWithFile());
                    preparedStatement.setString(++index, collectionEntity.getName());
                    preparedStatement.setString(++index, collectionEntity.getPosterPath());
                    preparedStatement.setString(++index, collectionEntity.getBackdropPath());
                });
        return collections.stream().map(CollectionEntity::getUuid).collect(Collectors.toList());
    }

    public List<CollectionEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM collections", this::fillCollectionByResultSet);
    }

    public Optional<CollectionEntity> findByUuid(final UUID uuid) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM collections WHERE uuid = ?",
                    this::fillCollectionByResultSet, uuid));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    private CollectionEntity fillCollectionByResultSet(ResultSet rs, int i) throws SQLException {
        CollectionEntity collection = new CollectionEntity();
        collection.setUuid(rs.getObject("uuid", UUID.class));
        collection.setIdWithFile(rs.getLong("id_with_file"));
        collection.setName(rs.getString("name"));
        collection.setPosterPath(rs.getString("poster_path"));
        collection.setBackdropPath(rs.getString("backdrop_path"));
        return collection;
    }
}
