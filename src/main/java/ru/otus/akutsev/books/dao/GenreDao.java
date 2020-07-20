package ru.otus.akutsev.books.dao;

import ru.otus.akutsev.books.model.Genre;

import java.util.Optional;

public interface GenreDao {

	Genre save(Genre genre);
	Optional<Genre> getAById(long id);
	void delete (Genre genre);

}
