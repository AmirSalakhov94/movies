package tech.itpark.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.dto.CollectionDto;
import tech.itpark.dto.enums.Status;
import tech.itpark.entity.MovieEntity;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<UUID> save(List<MovieEntity> movies, int batchSize) {
        jdbcTemplate.batchUpdate("INSERT INTO movies (uuid, id_with_file, is_adult, budget," +
                        " imdb_id, homepage, original_language, original_title, poster_path, overview, popularity, release_date," +
                        " revenue, runtime, status, tagline, title, is_video, vote_average, vote_count)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                movies, batchSize, (preparedStatement, movieEntity) -> {
                    int index = 0;
                    preparedStatement.setObject(++index, movieEntity.getUuid(), Types.OTHER);
                    preparedStatement.setLong(++index, movieEntity.getIdWithFile());
                    preparedStatement.setBoolean(++index, movieEntity.isAdult());
                    preparedStatement.setLong(++index, movieEntity.getBudget());
                    preparedStatement.setString(++index, movieEntity.getImdbId());
                    preparedStatement.setString(++index, movieEntity.getHomepage());
                    preparedStatement.setString(++index, movieEntity.getOriginalLanguage());
                    preparedStatement.setString(++index, movieEntity.getOriginalTitle());
                    preparedStatement.setString(++index, movieEntity.getPosterPath());
                    preparedStatement.setString(++index, movieEntity.getOverview());
                    preparedStatement.setFloat(++index, movieEntity.getPopularity());
                    preparedStatement.setObject(++index, movieEntity.getReleaseDate(), Types.DATE);
                    preparedStatement.setLong(++index, movieEntity.getRevenue());
                    preparedStatement.setFloat(++index, movieEntity.getRuntime());
                    preparedStatement.setString(++index, movieEntity.getStatus().name());
                    preparedStatement.setString(++index, movieEntity.getTagline());
                    preparedStatement.setString(++index, movieEntity.getTitle());
                    preparedStatement.setBoolean(++index, movieEntity.isVideo());
                    preparedStatement.setFloat(++index, movieEntity.getVoteAverage());
                    preparedStatement.setLong(++index, movieEntity.getVoteCount());
                });

        jdbcTemplate.batchUpdate("INSERT INTO movies_collections (movie_uuid, collection_uuid)" +
                " VALUES (? ,?)", movies, batchSize, ((preparedStatement, movie) -> {
            CollectionDto collection = movie.getCollection();
            int index = 0;
            preparedStatement.setObject(++index, movie.getUuid(), Types.OTHER);
            preparedStatement.setObject(++index, collection.getUuid(), Types.OTHER);
        }));

        movies.forEach(movie -> jdbcTemplate.batchUpdate("INSERT INTO movies_companies (movie_uuid, company_uuid)" +
                " VALUES (? ,?)", movie.getCompanies(), movie.getCompanies().size(), ((preparedStatement, company) -> {
            int index = 0;
            preparedStatement.setObject(++index, movie.getUuid(), Types.OTHER);
            preparedStatement.setObject(++index, company.getUuid(), Types.OTHER);
        })));

        movies.forEach(movie -> jdbcTemplate.batchUpdate("INSERT INTO movies_countries (movie_uuid, country_uuid)" +
                " VALUES (? ,?)", movie.getCountries(), movie.getCountries().size(), ((preparedStatement, country) -> {
            int index = 0;
            preparedStatement.setObject(++index, movie.getUuid(), Types.OTHER);
            preparedStatement.setObject(++index, country.getUuid(), Types.OTHER);
        })));

        movies.forEach(movie -> jdbcTemplate.batchUpdate("INSERT INTO movies_genres (movie_uuid, genre_uuid)" +
                " VALUES (? ,?)", movie.getGenres(), movie.getGenres().size(), ((preparedStatement, genre) -> {
            int index = 0;
            preparedStatement.setObject(++index, movie.getUuid(), Types.OTHER);
            preparedStatement.setObject(++index, genre.getUuid(), Types.OTHER);
        })));

        movies.forEach(movie -> jdbcTemplate.batchUpdate("INSERT INTO movies_languages (movie_uuid, language_uuid)" +
                " VALUES (? ,?)", movie.getLanguages(), movie.getLanguages().size(), ((preparedStatement, language) -> {
            int index = 0;
            preparedStatement.setObject(++index, movie.getUuid(), Types.OTHER);
            preparedStatement.setObject(++index, language.getUuid(), Types.OTHER);
        })));

        return movies.stream().map(MovieEntity::getUuid).collect(Collectors.toList());
    }

    public Optional<MovieEntity> findByUuid(UUID uuid) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM movies WHERE uuid = ?",
                (resultSet, i) -> {
                    MovieEntity movieEntity = new MovieEntity();
                    movieEntity.setUuid(resultSet.getObject("uuid", UUID.class));
                    movieEntity.setIdWithFile(resultSet.getLong("id_with_file"));
                    movieEntity.setAdult(resultSet.getBoolean("is_adult"));
                    movieEntity.setBudget(resultSet.getLong("budget"));
                    movieEntity.setImdbId(resultSet.getString("imdb_id"));
                    movieEntity.setHomepage(resultSet.getString("homepage"));
                    movieEntity.setOriginalLanguage(resultSet.getString("original_language"));
                    movieEntity.setOriginalTitle(resultSet.getString("original_title"));
                    movieEntity.setPosterPath(resultSet.getString("poster_path"));
                    movieEntity.setOverview(resultSet.getString("overview"));
                    movieEntity.setPopularity(resultSet.getFloat("popularity"));
                    movieEntity.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
                    movieEntity.setRevenue(resultSet.getLong("revenue"));
                    movieEntity.setRuntime(resultSet.getFloat("runtime"));
                    movieEntity.setStatus(Status.valueOf(resultSet.getString("status")));
                    movieEntity.setTagline(resultSet.getString("tagline"));
                    movieEntity.setTitle(resultSet.getString("title"));
                    movieEntity.setVideo(resultSet.getBoolean("is_video"));
                    movieEntity.setVoteAverage(resultSet.getFloat("vote_average"));
                    movieEntity.setVoteCount(resultSet.getLong("vote_count"));

                    return movieEntity;
                }, uuid))
                .map(movie -> {

                })
    }
}
