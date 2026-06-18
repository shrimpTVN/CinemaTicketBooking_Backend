--###ACTOR####
--id
--name
--avatar
--intro
--DoB
--gender

--##MOVIE-ACTOR
--movie_id
--actor_id

--###Movie####
-- id
-- title
-- duration
-- avatar
-- trailer
-- description
-- country
-- ageLimit
-- premiereDate
-- rate
-- actors
-- director
-- genres

CREATE TABLE IF NOT EXISTS movie(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title varchar(100) NOT NULL,
    duration int NOT NULL,
    avatar varchar(500) NOT NULL,
    trailer varchar(500) NOT NULL,
    description TEXT NOT NULL,
    country varchar(100) NOT NULL,
    ageLimit int default 0,
    premiereDate TIMESTAMP NOT NULL,
    rate float default 0,
    actors varchar(500) NOT NULL,
    director varchar(100) NOT NULL,
    genres varchar(500) NOT NULL,
    status VARCHAR(50) DEFAULT 'COMING SOON',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

