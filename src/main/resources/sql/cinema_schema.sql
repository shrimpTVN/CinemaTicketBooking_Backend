drop table if exists genre;
CREATE TABLE IF NOT EXISTS genre
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        varchar(100)                          NOT NULL UNIQUE,
    description text                                  NOT NULL,
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
);

drop table if exists movie;
CREATE TABLE IF NOT EXISTS movie
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    title         varchar(100)                          NOT NULL unique,
    duration      int                                   NOT NULL check (duration > 0),
    avatar        varchar(500)                          NOT NULL,
    trailer       varchar(500)                          NOT NULL,
    description   TEXT                                  NOT NULL,
    country       varchar(100)                          NOT NULL,
    age_limit     int         default 0 check (age_limit >= 0),
    premiere_date DATETIME                              NOT NULL,
    rating        float       default 0 check (rating >= 0 AND rating <= 10),
    actors        varchar(500)                          NOT NULL,
    director      varchar(100)                          NOT NULL,
    status        VARCHAR(50) DEFAULT 'COMING SOON',
    created_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by    VARCHAR(20)                           NOT NULL,
    updated_at    TIMESTAMP   DEFAULT NULL,
    updated_by    VARCHAR(20) DEFAULT NULL
);

drop table if exists movie_genre;
CREATE TABLE IF NOT EXISTS movie_genre
(
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre (id) ON DELETE CASCADE
);

create table if not exists hall_type
(
    id          int auto_increment primary key,
    name        varchar(100)                          not null unique,
    description text                                  not null,
    convenience text                                  not null,
    style       varchar(100)                          not null,
    images      JSON                                  not null,
    status      varchar(50) default 'ON',
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
);

drop table if exists hall;
create table if not exists hall
(
    id           int auto_increment primary key,
    name         varchar(100)                          not null unique,
    width        int                                   not null check (width > 0),
    height       int                                   not null check (height > 0),
    status       varchar(50) default 'ON',
    hall_type_id int                                   not null,
    created_at   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by   VARCHAR(20)                           NOT NULL,
    updated_at   TIMESTAMP   DEFAULT NULL,
    updated_by   VARCHAR(20) DEFAULT NULL,
    foreign key (hall_type_id) references hall_type (id) on delete restrict
);

create table if not exists seat_type
(
    id              int auto_increment primary key,
    name            varchar(100)   not null unique,
    price_surcharge decimal(10, 2) not null check (price_surcharge >= 0) default 0,
    description     text           not null,
    image           varchar(500)   not null,
    status          varchar(50)                                          default 'ON',
    created_at      TIMESTAMP                                            DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by      VARCHAR(20)    NOT NULL,
    updated_at      TIMESTAMP                                            DEFAULT NULL,
    updated_by      VARCHAR(20)                                          DEFAULT NULL
);


drop table if exists seat;
create table if not exists seat
(
    id           int auto_increment primary key,
    hall_id      int                                   not null,
    seat_type_id int                                   not null,
    row_label    varchar(5)                            not null,
    col_number   int                                   not null check (col_number > 0),
    status       varchar(50) default 'ON',
    created_at   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by   VARCHAR(20)                           NOT NULL,
    updated_at   TIMESTAMP   DEFAULT NULL,
    updated_by   VARCHAR(20) DEFAULT NULL,
    foreign key (hall_id) references hall (id) on delete restrict,
    foreign key (seat_type_id) references seat_type (id) on delete restrict
);

drop table if exists showtime;
create table if not exists showtime
(
    id         int auto_increment primary key,
    movie_id   int                                   not null,
    hall_id    int                                   not null,
    date       datetime                              not null,
    start_time TIME                                  not null,
    type       varchar(50)                           not null,
    created_at TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR(20)                           NOT NULL,
    updated_at TIMESTAMP   DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    foreign key (movie_id) references movie (id) on delete restrict,
    foreign key (hall_id) references hall (id) on delete restrict
);

drop table if exists showtime_seat;
create table if not exists showtime_seat
(
    showtime_id int                                   not null,
    seat_id     int                                   not null,
    status      varchar(50) default 'AVAILABLE',
    holdBy      int         DEFAULT 0,
    holdUntil   TIMESTAMP   DEFAULT NULL,
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL,
    primary key (showtime_id, seat_id),
    foreign key (showtime_id) references showtime (id) on delete cascade,
    foreign key (seat_id) references seat (id) on delete cascade
);

drop table if exists ticket;
create table if not exists ticket
(
    id          int auto_increment primary key,
    showtime_id int                                   not null,
    seat_id     int                                   not null,
    invoice_id  int                                   not null,
    price       decimal(10, 2)                        not null check (price >= 0),
    qr          varchar(500)                          not null,
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL,
    foreign key (showtime_id) references showtime (id) on delete restrict,
    foreign key (seat_id) references seat (id) on delete restrict,
    foreign key (invoice_id) references invoice (id) on delete restrict
);

create table if not exists invoice
(
    id             int auto_increment primary key,
    total_amount   decimal(10, 2)                        not null check (total_amount >= 0),
    payment_method varchar(50)                           not null,
    vat            decimal(5, 2)                         not null check (vat >= 0 AND vat <= 100),
    status         varchar(50) default 'PENDING',
    created_at     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by     VARCHAR(20)                           NOT NULL,
    updated_at     TIMESTAMP   DEFAULT NULL,
    updated_by     VARCHAR(20) DEFAULT NULL
);
ALTER TABLE invoice
    ADD COLUMN user_id int,
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
            REFERENCES user (id);

create table if not exists product
(
    id          int auto_increment primary key,
    name        varchar(100)                          not null,
    description text                                  not null,
    price       decimal(10, 2)                        not null check (price >= 0),
    image       varchar(500)                          not null,
    status      varchar(50) default 'ON',
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
);

create table if not exists invoice_detail
(
    invoice_id int                                   not null,
    product_id int                                   not null,
    quantity   int                                   not null check (quantity > 0),
    price      decimal(10, 2)                        not null check (price >= 0),
    created_at TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR(20)                           NOT NULL,
    updated_at TIMESTAMP   DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    foreign key (invoice_id) references invoice (id) on delete restrict,
    foreign key (product_id) references product (id) on delete restrict
);



create table if not exists user
(
    id           int auto_increment primary key,
    name         varchar(100)                          not null,
    DoB          date                                  not null,
    gender       varchar(10)                           not null,
    point        int         default 0 check (point >= 0),
    phone_number varchar(10)                           not null unique,
    email        varchar(255)                          not null unique,
    password     varchar(255)                          not null,
    status       varchar(50) default 'ON',
    created_at   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by   VARCHAR(20)                           NOT NULL,
    updated_at   TIMESTAMP   DEFAULT NULL,
    updated_by   VARCHAR(20) DEFAULT NULL
);

ALTER TABLE user
    ADD COLUMN role_id INT DEFAULT 2;
ALTER TABLE user
    ADD CONSTRAINT fk_user_role
        FOREIGN KEY (role_id) REFERENCES role (id);

create table if not exists role
(
    id          int auto_increment primary key,
    name        varchar(100)                          not null unique,
    description text                                  not null,
    status      varchar(50) default 'ON',
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
);

drop table if exists special_list;
create table if not exists special_list
(

    id          int auto_increment primary key,
    code        varchar(50)                           not null unique,
    name        varchar(100)                          not null unique,
    description text                                  not null,
    list        JSON                                  not null,
    status      varchar(50) default 'ON',
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
);

drop table if exists price_list;
create table if not exists price_list
(
    id               int auto_increment primary key,
    hall_type_id     int                                   not null,
    seat_type_id     int                                   not null,
    audience_type_id int                                   not null,
    name             varchar(100)                          not null,
    price            decimal(10, 2)                        not null check (price >= 0),
    days             JSON                                  not null,
    status           varchar(50) default 'ON',
    created_at       TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by       VARCHAR(20)                           NOT NULL,
    updated_at       TIMESTAMP   DEFAULT NULL,
    updated_by       VARCHAR(20) DEFAULT NULL,
    foreign key (hall_type_id) references hall_type (id) on delete restrict,
    foreign key (seat_type_id) references seat_type (id) on delete restrict,
    foreign key (audience_type_id) references audience_type (id) on delete restrict
);

drop table if exists audience_type;
create table if not exists audience_type
(
    id          int auto_increment primary key,
    name        varchar(100)                          not null unique,
    description text                                  not null,
    status      varchar(50) default 'ON',
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
);