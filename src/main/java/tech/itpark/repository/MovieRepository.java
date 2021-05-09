package tech.itpark.repository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.dto.enums.Status;
import tech.itpark.entity.*;

import java.sql.Types;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    @SneakyThrows
    public List<UUID> save(final List<MovieEntity> movies, final int batchSize) {
        if (movies == null || movies.isEmpty())
            return Collections.emptyList();

        List<Pair<UUID, UUID>> companiesPair = new ArrayList<>();
        List<Pair<UUID, UUID>> countriesPair = new ArrayList<>();
        List<Pair<UUID, UUID>> genresPair = new ArrayList<>();
        List<Pair<UUID, UUID>> languagesPair = new ArrayList<>();

        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            movies.forEach(m -> {
                m.getCompanies().forEach(c -> companiesPair.add(ImmutablePair.of(m.getUuid(), c.getUuid())));
                m.getCountries().forEach(c -> countriesPair.add(ImmutablePair.of(m.getUuid(), c.getUuid())));
                m.getGenres().forEach(g -> genresPair.add(ImmutablePair.of(m.getUuid(), g.getUuid())));
                m.getLanguages().forEach(l -> languagesPair.add(ImmutablePair.of(m.getUuid(), l.getUuid())));
            });

            return true;
        });

        jdbcTemplate.batchUpdate("INSERT INTO movies (uuid, id_with_file, is_adult, budget," +
                        " imdb_id, homepage, original_language, original_title, poster_path, overview, popularity, release_date," +
                        " revenue, runtime, status, tagline, title, is_video, vote_average, vote_count, collection_uuid)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
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
                    preparedStatement.setString(++index, movieEntity.getStatus() != null ? movieEntity.getStatus().name() : null);
                    preparedStatement.setString(++index, movieEntity.getTagline());
                    preparedStatement.setString(++index, movieEntity.getTitle());
                    preparedStatement.setBoolean(++index, movieEntity.isVideo());
                    preparedStatement.setFloat(++index, movieEntity.getVoteAverage());
                    preparedStatement.setLong(++index, movieEntity.getVoteCount());

                    CollectionEntity collection = movieEntity.getCollection();
                    if (collection != null) {
                        preparedStatement.setObject(++index, collection.getUuid(), Types.OTHER);
                    } else {
                        preparedStatement.setObject(++index, null);
                    }
                });

        if (Boolean.TRUE.equals(future.get())) {
            ExecutorService worker = Executors.newFixedThreadPool(4);
            List<Callable<Boolean>> callables = Arrays.asList(
                    () -> {
                        jdbcTemplate.batchUpdate("INSERT INTO movies_companies (movie_uuid, company_uuid)" +
                                " VALUES (?, ?)", companiesPair, batchSize, ((preparedStatement, pair) -> {
                            int index = 0;
                            preparedStatement.setObject(++index, pair.getLeft(), Types.OTHER);
                            preparedStatement.setObject(++index, pair.getRight(), Types.OTHER);
                        }));

                        return true;
                    },
                    () -> {
                        jdbcTemplate.batchUpdate("INSERT INTO movies_countries (movie_uuid, country_uuid)" +
                                " VALUES (?, ?)", countriesPair, batchSize, ((preparedStatement, pair) -> {
                            int index = 0;
                            preparedStatement.setObject(++index, pair.getLeft(), Types.OTHER);
                            preparedStatement.setObject(++index, pair.getRight(), Types.OTHER);
                        }));

                        return true;
                    },
                    () -> {
                        jdbcTemplate.batchUpdate("INSERT INTO movies_genres (movie_uuid, genre_uuid)" +
                                " VALUES (?, ?)", genresPair, batchSize, ((preparedStatement, pair) -> {
                            int index = 0;
                            preparedStatement.setObject(++index, pair.getLeft(), Types.OTHER);
                            preparedStatement.setObject(++index, pair.getRight(), Types.OTHER);
                        }));

                        return true;
                    },
                    () -> {
                        jdbcTemplate.batchUpdate("INSERT INTO movies_languages (movie_uuid, language_uuid)" +
                                " VALUES (?, ?)", languagesPair, batchSize, ((preparedStatement, pair) -> {
                            int index = 0;
                            preparedStatement.setObject(++index, pair.getLeft(), Types.OTHER);
                            preparedStatement.setObject(++index, pair.getRight(), Types.OTHER);
                        }));

                        return true;
                    }
            );

            worker.invokeAll(callables);
        }

        return movies.stream().map(MovieEntity::getUuid).collect(Collectors.toList());
    }

    public Optional<MovieEntity> findByUuid(final UUID uuid) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM movies m" +
                        " LEFT JOIN collections c on m.collection_uuid = c.uuid" +
                        " WHERE m.uuid = ?",
                (rs, i) -> {
                    int index = 0;
                    MovieEntity movieEntity = new MovieEntity();
                    movieEntity.setUuid(rs.getObject(++index, UUID.class));
                    movieEntity.setIdWithFile(rs.getLong(++index));
                    movieEntity.setAdult(rs.getBoolean(++index));
                    movieEntity.setBudget(rs.getLong(++index));
                    movieEntity.setImdbId(rs.getString(++index));
                    movieEntity.setHomepage(rs.getString(++index));
                    movieEntity.setOriginalLanguage(rs.getString(++index));
                    movieEntity.setOriginalTitle(rs.getString(++index));
                    movieEntity.setPosterPath(rs.getString(++index));
                    movieEntity.setOverview(rs.getString(++index));
                    movieEntity.setPopularity(rs.getFloat(++index));
                    movieEntity.setReleaseDate(rs.getDate(++index).toLocalDate());
                    movieEntity.setRevenue(rs.getLong(++index));
                    movieEntity.setRuntime(rs.getFloat(++index));
                    movieEntity.setStatus(Status.valueOf(rs.getString(++index)));
                    movieEntity.setTagline(rs.getString(++index));
                    movieEntity.setTitle(rs.getString(++index));
                    movieEntity.setVideo(rs.getBoolean(++index));
                    movieEntity.setVoteAverage(rs.getFloat(++index));
                    movieEntity.setVoteCount(rs.getLong(++index));

                    ++index;
                    CollectionEntity collectionEntity = new CollectionEntity();
                    collectionEntity.setUuid(rs.getObject(++index, UUID.class));
                    collectionEntity.setIdWithFile(rs.getLong(++index));
                    collectionEntity.setName(rs.getString(++index));
                    collectionEntity.setPosterPath(rs.getString(++index));
                    collectionEntity.setBackdropPath(rs.getString(++index));
                    movieEntity.setCollection(collectionEntity);

                    return movieEntity;
                }, uuid))
                .map(movie -> {
                    List<CompanyEntity> companies = jdbcTemplate.query("SELECT * FROM companies" +
                                    " JOIN movies_companies mc ON companies.uuid = mc.company_uuid" +
                                    " WHERE mc.movie_uuid = ?",
                            (rs, i) -> {
                                CompanyEntity company = new CompanyEntity();
                                company.setUuid(rs.getObject("uuid", UUID.class));
                                company.setIdWithFile(rs.getLong("id_with_file"));
                                company.setName(rs.getString("name"));

                                return company;
                            }, uuid);
                    movie.setCompanies(companies);

                    List<CountryEntity> countries = jdbcTemplate.query("SELECT * FROM countries" +
                                    " JOIN movies_countries mc ON countries.uuid = mc.country_uuid" +
                                    " WHERE mc.movie_uuid = ?",
                            (rs, i) -> {
                                CountryEntity country = new CountryEntity();
                                country.setUuid(rs.getObject("uuid", UUID.class));
                                country.setIso31661(rs.getString("iso_3166_1"));
                                country.setName(rs.getString("name"));

                                return country;
                            }, uuid);
                    movie.setCountries(countries);

                    List<GenreEntity> genres = jdbcTemplate.query("SELECT * FROM genres" +
                                    " JOIN movies_genres mg ON genres.uuid = mg.genre_uuid" +
                                    " WHERE mg.movie_uuid = ?",
                            (rs, i) -> {
                                GenreEntity genre = new GenreEntity();
                                genre.setUuid(rs.getObject("uuid", UUID.class));
                                genre.setIdWithFile(rs.getLong("id_with_file"));
                                genre.setName(rs.getString("name"));

                                return genre;
                            }, uuid);
                    movie.setGenres(genres);

                    List<LanguageEntity> languages = jdbcTemplate.query("SELECT * FROM languages" +
                                    " JOIN movies_languages mg ON languages.uuid = mg.language_uuid" +
                                    " WHERE mg.movie_uuid = ?",
                            (rs, i) -> {
                                LanguageEntity language = new LanguageEntity();
                                language.setUuid(rs.getObject("uuid", UUID.class));
                                language.setIso6391(rs.getString("iso_639_1"));
                                language.setName(rs.getString("name"));

                                return language;
                            }, uuid);
                    movie.setLanguages(languages);

                    return movie;
                });
    }

    public List<PreviewMovieEntity> findTopMoviesByGenre(final UUID genreUuid, final int count) {
        return jdbcTemplate.query("SELECT m.uuid, m.homepage, m.original_title, m.poster_path, " +
                        "m.vote_average, m.vote_count, g.name FROM movies m " +
                        "JOIN movies_genres mg on m.uuid = mg.movie_uuid " +
                        "JOIN genres g on g.uuid = mg.genre_uuid " +
                        "WHERE g.uuid = ? ORDER BY m.vote_average DESC LIMIT ?",
                (rs, i) -> {
                    PreviewMovieEntity previewMovie = new PreviewMovieEntity();
                    previewMovie.setUuid(rs.getObject("uuid", UUID.class));
                    previewMovie.setHomepage(rs.getString("homepage"));
                    previewMovie.setOriginalTitle(rs.getString("original_title"));
                    previewMovie.setPosterPath(rs.getString("poster_path"));
                    previewMovie.setVoteAverage(rs.getFloat("vote_average"));
                    previewMovie.setVoteCount(rs.getLong("vote_count"));
                    previewMovie.setGenre(rs.getString("name"));
                    return previewMovie;
                }, genreUuid, count);
    }

    public List<PreviewMovieEntity> moviesByCompany(final UUID companyUuid) {
        return jdbcTemplate.query("SELECT m.uuid, m.homepage, m.original_title, m.poster_path, " +
                        "m.vote_average, m.vote_count, c.name FROM movies m " +
                        "JOIN movies_companies mc on m.uuid = mc.movie_uuid " +
                        "JOIN companies c on c.uuid = mc.company_uuid " +
                        "WHERE c.uuid = ? ORDER BY m.release_date DESC LIMIT ?",
                (rs, i) -> {
                    PreviewMovieEntity previewMovie = new PreviewMovieEntity();
                    previewMovie.setUuid(rs.getObject("uuid", UUID.class));
                    previewMovie.setHomepage(rs.getString("homepage"));
                    previewMovie.setOriginalTitle(rs.getString("original_title"));
                    previewMovie.setPosterPath(rs.getString("poster_path"));
                    previewMovie.setVoteAverage(rs.getFloat("vote_average"));
                    previewMovie.setVoteCount(rs.getLong("vote_count"));
                    previewMovie.setCompany(rs.getString("name"));
                    return previewMovie;
                }, companyUuid);
    }

    public List<PreviewMovieEntity> findTopMovies(final int count) {
        return jdbcTemplate.query("SELECT m.uuid, m.homepage, m.original_title, m.poster_path, " +
                        "m.vote_average, m.vote_count, g.name FROM movies m " +
                        "JOIN movies_genres mg on m.uuid = mg.movie_uuid " +
                        "JOIN genres g on g.uuid = mg.genre_uuid " +
                        "ORDER BY m.vote_average DESC LIMIT ?",
                (rs, i) -> {
                    PreviewMovieEntity previewMovie = new PreviewMovieEntity();
                    previewMovie.setUuid(rs.getObject("uuid", UUID.class));
                    previewMovie.setHomepage(rs.getString("homepage"));
                    previewMovie.setOriginalTitle(rs.getString("original_title"));
                    previewMovie.setPosterPath(rs.getString("poster_path"));
                    previewMovie.setVoteAverage(rs.getFloat("vote_average"));
                    previewMovie.setVoteCount(rs.getLong("vote_count"));
                    previewMovie.setGenre(rs.getString("name"));
                    return previewMovie;
                }, count);
    }

    public List<PreviewMovieEntity> findAll(final int offset, final int num) {
        return jdbcTemplate.query("SELECT m.uuid, m.homepage, m.original_title, m.poster_path, " +
                        "m.vote_average, m.vote_count FROM movies m ORDER BY m.vote_average DESC OFFSET ? LIMIT ?",
                (rs, i) -> {
                    PreviewMovieEntity previewMovie = new PreviewMovieEntity();
                    previewMovie.setUuid(rs.getObject("uuid", UUID.class));
                    previewMovie.setHomepage(rs.getString("homepage"));
                    previewMovie.setOriginalTitle(rs.getString("original_title"));
                    previewMovie.setPosterPath(rs.getString("poster_path"));
                    previewMovie.setVoteAverage(rs.getFloat("vote_average"));
                    previewMovie.setVoteCount(rs.getLong("vote_count"));
                    return previewMovie;
                }, offset, num);
    }

    public List<PreviewMovieEntity> moviesByCollection(final UUID collectionUuid) {
        return jdbcTemplate.query("SELECT m.uuid, m.homepage, m.original_title, m.poster_path, " +
                        "m.vote_average, m.vote_count FROM movies m " +
                        "WHERE m.collection_uuid = ? ORDER BY m.release_date DESC",
                (rs, i) -> {
                    PreviewMovieEntity previewMovie = new PreviewMovieEntity();
                    previewMovie.setUuid(rs.getObject("uuid", UUID.class));
                    previewMovie.setHomepage(rs.getString("homepage"));
                    previewMovie.setOriginalTitle(rs.getString("original_title"));
                    previewMovie.setPosterPath(rs.getString("poster_path"));
                    previewMovie.setVoteAverage(rs.getFloat("vote_average"));
                    previewMovie.setVoteCount(rs.getLong("vote_count"));
                    return previewMovie;
                }, collectionUuid);
    }
}
