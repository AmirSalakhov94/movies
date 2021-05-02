package tech.itpark.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EntityManager {

    private static final String SELECT_QUERY = "SELECT :columns FROM :table ORDER BY :sort OFFSET :offset LIMIT :num";
    private final NamedParameterJdbcOperations template;

    public <T> List<T> findAll(Class<T> clazz) {
        return null;
    }

    public <T> Optional<T> findById(Class<T> clazz) {
        return Optional.empty();
    }
}
