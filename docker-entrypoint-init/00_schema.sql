CREATE TABLE movies
(
    uuid              UUID PRIMARY KEY,
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
    uuid          UUID PRIMARY KEY,
    id_with_file  BIGSERIAL,
    name          TEXT,
    poster_path   TEXT,
    backdrop_path TEXT
);

CREATE TABLE movies_collections
(
    movie_uuid      UUID NOT NULL REFERENCES movies (uuid),
    collection_uuid UUID NOT NULL REFERENCES collections (uuid)
);

CREATE TABLE companies
(
    uuid         UUID PRIMARY KEY,
    id_with_file BIGSERIAL,
    name         TEXT
);

CREATE TABLE movies_companies
(
    movie_uuid   UUID NOT NULL REFERENCES movies (uuid),
    company_uuid UUID NOT NULL REFERENCES companies (uuid)
);

CREATE TABLE countries
(
    uuid       UUID PRIMARY KEY,
    iso_3166_1 TEXT,
    name       TEXT
);

CREATE TABLE movies_countries
(
    movie_uuid   UUID NOT NULL REFERENCES movies (uuid),
    country_uuid UUID NOT NULL REFERENCES countries (uuid)
);

CREATE TABLE genres
(
    uuid         UUID PRIMARY KEY,
    id_with_file BIGSERIAL,
    name         TEXT
);

CREATE TABLE movies_genres
(
    movie_uuid UUID NOT NULL REFERENCES movies (uuid),
    genre_uuid UUID NOT NULL REFERENCES genres (uuid)
);

CREATE TABLE languages
(
    uuid      UUID PRIMARY KEY,
    iso_639_1 TEXT,
    name      TEXT
);

CREATE TABLE movies_languages
(
    movie_uuid    UUID NOT NULL REFERENCES movies (uuid),
    language_uuid UUID NOT NULL REFERENCES languages (uuid)
);