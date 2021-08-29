create table LEGO
(
    id           SERIAL PRIMARY KEY,
    url          VARCHAR(250) NOT NULL,
    name         VARCHAR(150) NOT NULL,
    priceInCents INTEGER      NOT NULL
);
