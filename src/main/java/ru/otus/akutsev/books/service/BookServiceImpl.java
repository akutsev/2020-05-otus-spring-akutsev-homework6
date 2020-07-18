package ru.otus.akutsev.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.akutsev.books.dao.BookDao;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

	private final BookDao bookDao;

	@Autowired
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	@Transactional
	public Book add(Book book) {
		return bookDao.add(book);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Book> getAById(long id) {
		return bookDao.getAById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getAll() {
		return bookDao.getAll();
	}

	@Override
	@Transactional
	public void updateBook (Book book, String newName, Author newAuthor, Genre newGenre) {
		bookDao.updateBook(book, newName, newAuthor, newGenre);
	}

	@Override
	@Transactional
	public void delete (int id) {
		bookDao.delete(id);
	}
}
