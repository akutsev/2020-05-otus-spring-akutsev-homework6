package ru.otus.akutsev.books.service;

import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {

	Book save(Book book);
	Optional<Book> getAById(long id);
	List<Book> getAll();
	void updateBook (Book book, String newName, Author newAuthor, Genre newGenre);
	List<Comment> getAllComments(Book book);
	void delete (Book book);

}
