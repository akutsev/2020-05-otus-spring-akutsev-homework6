package ru.otus.akutsev.books.dao;

import ru.otus.akutsev.books.model.Author;

import java.util.Optional;

public interface AuthorDao {

	public Author add(Author author);
	public Optional<Author> getAById(long id);
	public void updateName(Author author, String newName);
	public void delete (long id);

}