CREATE TABLE user (
  id SERIAL PRIMARY KEY,
  name  TEXT,
  email VARCHAR(70),
  password varchar(15)
);

ALTER TABLE user ADD CONSTRAINT email_unique UNIQUE (email);