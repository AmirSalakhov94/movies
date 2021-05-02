CREATE TABLE movies
(
    id                BIGSERIAL PRIMARY KEY,
    id_with_file      BIGSERIAL,
    is_adult          BOOLEAN,
    budget            BIGSERIAL,
    imdb_id           TEXT,
    homepage          TEXT,
    original_language TEXT,
    original_title    TEXT,
    poster_path       TEXT,
    overview          TEXT,
    popularity        REAL,
    release_date      DATE,
    revenue           BIGSERIAL,
    runtime           REAL,
    status            TEXT,
    tagline           TEXT,
    title             TEXT,
    is_video          BOOLEAN,
    vote_average      REAL,
    vote_count        BIGSERIAL
);

CREATE TABLE collections
(
    id            BIGSERIAL PRIMARY KEY,
    id_with_file  BIGSERIAL,
    name          TEXT,
    poster_path   TEXT,
    backdrop_path TEXT
);
CREATE TABLE movies_collections
(
    movie_id      BIGSERIAL NOT NULL REFERENCES movies (id),
    collection_id BIGSERIAL NOT NULL REFERENCES collections (id)
);

CREATE TABLE companies
(
    id           BIGSERIAL PRIMARY KEY,
    id_with_file BIGSERIAL,
    name         TEXT
);
CREATE TABLE movies_companies
(
    movie_id   BIGSERIAL NOT NULL REFERENCES movies (id),
    company_id BIGSERIAL NOT NULL REFERENCES companies (id)
);

CREATE TABLE countries
(
    id         BIGSERIAL PRIMARY KEY,
    iso_3166_1 TEXT,
    name       TEXT
);
CREATE TABLE movies_countries
(
    movie_id   BIGSERIAL NOT NULL REFERENCES movies (id),
    country_id BIGSERIAL NOT NULL REFERENCES countries (id)
);

CREATE TABLE genres
(
    id           BIGSERIAL PRIMARY KEY,
    id_with_file BIGSERIAL,
    name         TEXT
);
CREATE TABLE movies_genres
(
    movie_id BIGSERIAL NOT NULL REFERENCES movies (id),
    genre_id BIGSERIAL NOT NULL REFERENCES genres (id)
);

CREATE TABLE languages
(
    id        BIGSERIAL PRIMARY KEY,
    iso_639_1 TEXT,
    name      TEXT
);
CREATE TABLE movies_languages
(
    movie_id    BIGSERIAL NOT NULL REFERENCES movies (id),
    language_id BIGSERIAL NOT NULL REFERENCES languages (id)
);