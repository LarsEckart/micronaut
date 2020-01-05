-- V1__create_account_table.sql

CREATE TABLE ACCOUNT (
    id INT NOT NULL,
    amount DECIMAL NOT NULL,
    iban VARCHAR(30) NOT NULL
);
