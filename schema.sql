DROP
DATABASE IF EXISTS papertrading;

CREATE
DATABASE papertrading;

USE
papertrading;

CREATE TABLE listings
(
    asset_id  varchar(10) PRIMARY KEY,
    price_usd float
);


-- Initializaing the table with some token symbols
INSERT INTO listings (asset_id, price_usd)
VALUES ('BTC', 40819.44),
       ('ETH', 2682.87),
       ('BNB', 388.81),
       ('XRP', 0.74),
       ('LUNA', 90.79),
       ('SOL', 91.87),
       ('ADA', 0.87),
       ('AVAX', 79.39),
       ('DOT', 17.15);


CREATE TABLE users
(
    uid      int PRIMARY KEY AUTO_INCREMENT,
    username varchar(64)  NOT NULL UNIQUE,
    password varchar(256) NOT NULL,
    email    varchar(64)  NOT NULL UNIQUE,
    mobile   int,
    balance  float        NOT NULL DEFAULT 10000 CHECK (balance > 0)
);

-- Initialise the users table with some values
INSERT INTO users (username, password, email, mobile)
VALUES ('paperhand', SHA1('root'), 'paper@gmail.com', 91234567),
       ('diamondhand', SHA1('root'), 'diamond@gmail.com', 97654321),
       ('root', SHA1('root'), 'growroot@gmail.com', 90921237);


CREATE TABLE transactions
(
    txn_number  int PRIMARY KEY AUTO_INCREMENT,
    asset_id    varchar(64),
    quantity    float,
    price_usd   float,
    fk_username varchar(64),
    last_update timestamp
        default current_timestamp
        on update current_timestamp,
    foreign key (fk_username)
        references users (username)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);
