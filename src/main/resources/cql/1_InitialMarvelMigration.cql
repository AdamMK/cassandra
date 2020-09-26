CREATE TABLE IF NOT EXISTS marvel.heroes(id int PRIMARY KEY,nickname text,gender text,race text,year_created int);
CREATE INDEX IF NOT EXISTS nickname_index ON marvel.heroes (nickname);