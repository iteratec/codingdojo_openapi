
CREATE TABLE animal
(
    id         INTEGER     NOT NULL AUTO_INCREMENT,
    name       VARCHAR(36) NOT NULL UNIQUE,
    max_age    INTEGER     NOT NULL,
    aggressive BOOLEAN DEFAULT false,
    venomous   BOOLEAN DEFAULT false,
    indigenous BOOLEAN DEFAULT false,
    mystical   BOOLEAN DEFAULT false,
    creation   TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (id)
);

INSERT INTO animal (name, max_age, aggressive, indigenous, mystical)
VALUES ('Unicorn', 120, false, false, true),
       ('Dragon', 3500, true, false, true),
       ('Dog', 30, false, true, false),
       ('Cat', 38, false, true, false),
       ('Radiated tortoise', 188, true, false, false);
