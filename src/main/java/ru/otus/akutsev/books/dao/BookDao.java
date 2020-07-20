package ru.otus.akutsev.books.dao;

import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {

	Book save(Book book);
	Optional<Book> getAById(long id);
	List<Book> getAll();
	void delete (Book book);

}
