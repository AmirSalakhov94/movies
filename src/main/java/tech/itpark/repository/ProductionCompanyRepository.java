package tech.itpark.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.entity.CompanyEntity;

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
public class ProductionCompanyRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<UUID> save(final List<CompanyEntity> companies, final int batchSize) {
        if (companies == null || companies.isEmpty())
            return Collections.emptyList();

        jdbcTemplate.batchUpdate("INSERT INTO companies (uuid, id_with_file, name) values (?, ?, ?)",
                companies, batchSize, (preparedStatement, companyEntity) -> {
                    int index = 0;
                    preparedStatement.setObject(++index, companyEntity.getUuid(), Types.OTHER);
                    preparedStatement.setLong(++index, companyEntity.getIdWithFile());
                    preparedStatement.setString(++index, companyEntity.getName());
                });
        return companies.stream().map(CompanyEntity::getUuid).collect(Collectors.toList());
    }

    public List<CompanyEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM genres", this::fillCompanyByResultSet);
    }

    public Optional<CompanyEntity> findByUuid(final UUID uuid) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM genres WHERE uuid = ?",
                    this::fillCompanyByResultSet, uuid));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    private CompanyEntity fillCompanyByResultSet(ResultSet rs, int i) throws SQLException {
        CompanyEntity company = new CompanyEntity();
        company.setUuid(rs.getObject("uuid", UUID.class));
        company.setIdWithFile(rs.getLong("id_with_file"));
        company.setName(rs.getString("name"));
        return company;
    }
}
