package tech.itpark.jdbc;

import tech.itpark.exception.AmbiguousResultException;
import tech.itpark.exception.DataAccessException;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface JdbcTemplate {

    static <T> List<T> queryList(DataSource ds, String sql, RowMapper<T> rowMapper, Object... args) {
        return execute(ds, sql, args, stmt -> {
            try (
                    final var rs = stmt.executeQuery()
            ) {
                List<T> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(rowMapper.mapRow(rs));
                }
                return result;
            }
        });
    }

    static void executeWithoutResult(DataSource ds, String sql, Object... args) {
        execute(ds, sql, args, PreparedStatement::execute);
    }

    static <T> Optional<T> querySingle(DataSource ds, String sql, RowMapper<T> rowMapper, Object... args) {
        return execute(ds, sql, args, stmt -> {
            try (
                    final var rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    T element = rowMapper.mapRow(rs);
                    if (rs.next())
                        throw new AmbiguousResultException("expected one element result");

                    return Optional.of(element);
                }

                return Optional.empty();
            }
        });
    }

    private static <T> T execute(DataSource ds, String sql, Object[] args, Executor<T> executor) {
        try (
                final var conn = ds.getConnection();
                final var stmt = conn.prepareStatement(sql)
        ) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                stmt.setObject(i + 1, arg);
            }

            return executor.execute(stmt);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}
