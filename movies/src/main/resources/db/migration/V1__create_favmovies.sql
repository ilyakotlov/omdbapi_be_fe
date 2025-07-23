CREATE TABLE favmovies (
    id       BIGSERIAL PRIMARY KEY,
    imdb_id  VARCHAR(15)  NOT NULL UNIQUE,
    title    VARCHAR(255) NOT NULL,
    year     VARCHAR(9)   NOT NULL
);

CREATE INDEX idx_favmovies_title_year
    ON favmovies (title, year);