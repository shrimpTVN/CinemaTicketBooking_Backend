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

CREATE TABLE IF NOT EXISTS movie_genre
(
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT exists movie_record
(
    id         INT auto_increment primary key,
    type       varchar(50)                           not null,
    list       JSON                                  not null,
    created_at TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR(20)                           NOT NULL,
    updated_at TIMESTAMP   DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    constraint check_list check (JSON_TYPE(list) = 'ARRAY')
);


create table if not exists hall
(
    id          int auto_increment primary key,
    name        varchar(100)                          not null unique,
    width       int                                   not null check (width > 0),
    height      int                                   not null check (height > 0),
    images      JSON                                  not null,
    description text                                  not null,
    convenience text                                  not null,
    status      varchar(50) default 'ON',
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL,
    constraint check_images check (JSON_TYPE(images) = 'ARRAY')
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


create table if not exists showtime
(
    id         int auto_increment primary key,
    movie_id   int                                   not null,
    hall_id    int                                   not null,
    date       datetime                              not null,
    start_time TIME                                  not null,
    created_at TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR(20)                           NOT NULL,
    updated_at TIMESTAMP   DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    foreign key (movie_id) references movie (id) on delete restrict,
    foreign key (hall_id) references hall (id) on delete restrict
);

create table if not exists showtime_seat
(
    showtime_id int                                   not null,
    seat_id     int                                   not null,
    status      varchar(50) default 'AVAILABLE',
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL,
    primary key (showtime_id, seat_id),
    foreign key (showtime_id) references showtime (id) on delete cascade,
    foreign key (seat_id) references seat (id) on delete cascade
);

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

create table if not exists price_list
(
    id           int auto_increment primary key,
    hall_id      int                                   not null,
    seat_type_id int                                   not null,
    type         varchar(100)                          not null,
    price        decimal(10, 2)                        not null check (price >= 0),
    days         varchar(20)                           not null,
    status       varchar(50) default 'ON',
    created_at   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by   VARCHAR(20)                           NOT NULL,
    updated_at   TIMESTAMP   DEFAULT NULL,
    updated_by   VARCHAR(20) DEFAULT NULL,
    foreign key (hall_id) references hall (id) on delete restrict,
    foreign key (seat_type_id) references seat_type (id) on delete restrict
);

create table if not exists user
(
    id           int auto_increment primary key,
    name         varchar(100) not null,
    DoB          date         not null,
    gender       varchar(10)  not null,
    point        int          not null default 0 check (point >= 0),
    phone_number varchar(10)  not null unique,
    email        varchar(50)  not null unique,
    password     varchar(50)  not null,
    role         varchar(20)  not null default 'USER',
    status       varchar(50)           default 'ON',
    created_at   TIMESTAMP             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by   VARCHAR(20)  NOT NULL,
    updated_at   TIMESTAMP             DEFAULT NULL,
    updated_by   VARCHAR(20)           DEFAULT NULL
);