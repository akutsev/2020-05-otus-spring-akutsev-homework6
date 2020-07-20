package ru.otus.akutsev.books.dao;

import ru.otus.akutsev.books.model.Author;

import java.util.Optional;

public interface AuthorDao {

	Author save(Author author);
	Optional<Author> getAById(long id);
	void delete (Author author);

}