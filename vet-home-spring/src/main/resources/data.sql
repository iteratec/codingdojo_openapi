DROP TABLE IF EXISTS boxes, occupants, shelter;

CREATE TABLE shelter
(
    id      INTEGER    NOT NULL AUTO_INCREMENT,
    plz     VARCHAR(4) NOT NULL UNIQUE,
    contact VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE occupants
(
    id            INTEGER     NOT NULL AUTO_INCREMENT,
    reference_id  UUID     NOT NULL UNIQUE,
    animal_id     INTEGER     NOT NULL,
    animal        VARCHAR(35) NOT NULL,
    birth_day     DATE        NOT NULL,
    shelter_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    adoption_date TIMESTAMP NULL,
    PRIMARY KEY (id)
);

CREATE TABLE boxes
(
    id          INTEGER     NOT NULL AUTO_INCREMENT,
    label       VARCHAR(25) NOT NULL,
    strong      BOOLEAN DEFAULT false,
    shelter_id  INTEGER     NOT NULL,
    occupant_id VARCHAR NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (shelter_id) REFERENCES shelter (id),
    FOREIGN KEY (occupant_id) REFERENCES occupants (id)
);

-- registered shelters
INSERT INTO shelter (plz, contact)
VALUES ('1092', 'Fr. Tierlieb, tierlieb@unbekannt.at'),
       ('3112', 'Hr. Hundsgesicht'),
       ('5902', '+43 666 123456789');

-- current occupants
INSERT INTO occupants (reference_id, animal_id, animal, birth_day)
VALUES ('6e405434-6bd6-45d1-b20f-c1915c2246b5', '1', 'Unicorn', '1960-02-19'),
       ('9c2a3a59-d391-4b4c-aa51-a9f0d9ff2bd5', '3', 'Dog', '2012-10-01');

-- previously adopted
INSERT INTO occupants (reference_id, animal_id, animal, birth_day, shelter_date, adoption_date)
VALUES ('386b3c4c-09cd-4d7d-b105-17a883a28509', '3', 'Dog', '2022-02-01', '2022-09-12 10:30:00.000',
        '2022-09-30 11:59:59.000'),
       ('ae3c9dc6-598c-498a-ad43-9542211eaaf2', '4', 'Cat', '2021-11-11', '2021-12-29 09:00:15.999',
        '2021-12-31 23:59:59.999');

-- empty boxes
INSERT INTO boxes (shelter_id, label, strong)
VALUES (1, 'Box 1', false),
       (1, 'Box 2', false),
       (1, 'Box 3', false),
       (1, 'Box 4', false),
       (1, 'Zwinger 1', true),
       (1, 'Zwinger 2', true),
       (2, 'Box 1', false),
       (2, 'Box 2', false),
       (2, 'Zwinger', true),
       (3, 'Laufstall', false);

INSERT INTO boxes (shelter_id, label, occupant_id)
VALUES (1, 'Box 5', 1),
       (2, 'Box 3', 2);
