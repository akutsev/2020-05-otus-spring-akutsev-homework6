package ru.otus.akutsev.books.service;

import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {

	public Book add(Book book);
	public Optional<Book> getAById(long id);
	public List<Book> getAll();
	public void updateBook (Book book, String newName, Author newAuthor, Genre newGenre);
	public void delete (int id);

}
