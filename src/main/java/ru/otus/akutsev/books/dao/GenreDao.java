package ru.otus.akutsev.books.dao;

import ru.otus.akutsev.books.model.Genre;

import java.util.Optional;

public interface GenreDao {

	public Genre add(Genre genre);
	public Optional<Genre> getAById(long id);
	public void updateGenreName(Genre genre, String newName);
	public void delete (long id);
}
