DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS comments;

CREATE TABLE genres (
  ID INT NOT NULL AUTO_INCREMENT,
  GENRE_NAME VARCHAR(250) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE authors (
  ID INT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(250) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE books (
  ID INT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(250) NOT NULL,
  AUTHOR_ID INT NOT NULL,
  GENRE_ID INT NOT NULL,
  PRIMARY KEY (ID),
  foreign key (AUTHOR_ID) references authors(ID),
  foreign key (GENRE_ID) references genres(ID)
);

CREATE TABLE comments (
  ID INT NOT NULL AUTO_INCREMENT,
  TEXT VARCHAR(250) NOT NULL,
  BOOK_ID INT NOT NULL,
  PRIMARY KEY (ID),
  foreign key (BOOK_ID) references books(ID) ON DELETE CASCADE ON UPDATE CASCADE
);