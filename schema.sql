DROP DATABASE IF EXISTS papertrading; 

CREATE DATABASE papertrading; 

USE papertrading; 

CREATE TABLE listings (
    asset_id varchar(64) PRIMARY KEY, 
    price_usd varchar(64)
); 


-- Initializaing the table with some symbols 
INSERT INTO listings (asset_id, price_usd)
VALUES  
    ('BTC', 0), 
    ('ETH', 0),
    ('SOL', 0),
    ('CRO', 0);


CREATE TABLE users (
	uid int PRIMARY KEY AUTO_INCREMENT,
    name varchar(64) NOT NULL,
    password varchar(256) NOT NULL,
    email varchar(64) NOT NULL,
    mobile int
);

-- Initialise the users table with some values 
INSERT INTO users (name, password, email, mobile)
VALUES 
('Paper Hand', SHA1('root1234'), 'paper@gmail.com', 91234567),
('Diamond Hand', SHA1('root1234'), 'diamond@gmail.com', 97654321);


CREATE TABLE users_holding(
	txn_number int PRIMARY KEY AUTO_INCREMENT,
    asset_id varchar(64), 
    quantity decimal(30,10), 
    fk_uid int,
	foreign key (fk_uid)
        references users(uid)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

INSERT INTO users_holding (asset_id, quantity, fk_uid)
VALUES
('ETH', 2, 1),
('SOL', 10, 1),
('CRO', 2000, 1),
('BTC', 0.3, 2),
('CRO', 1521, 2),
('SOL', 200, 2),
('ETH', 1.2, 2),
('BTC', -0.1, 2);



SELECT * FROM USERS_HOLDING;

-- To display holdings of users 
SELECT a.fk_uid, a.asset_id, SUM(a.quantity) as quantity, (SUM(a.quantity) * b.price_usd) as value 
FROM users_holding a
JOIN listings b
ON a.asset_id = b.asset_id
GROUP BY a.fk_uid, a.asset_id, b.name;

-- To display leaderboard 
SELECT d.name, sum(c.value) as total_value 
FROM 
	(
		SELECT a.fk_uid, a.asset_id, SUM(a.quantity) as quantity, (SUM(a.quantity) * b.price_usd) as value 
		FROM users_holding a
		JOIN listings b
		ON a.asset_id = b.asset_id
		GROUP BY a.fk_uid, a.asset_id
    ) c
JOIN users d
ON c.fk_uid = d.uid
GROUP BY d.name
ORDER BY total_value DESC
;
    

