package ru.otus.akutsev.books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.akutsev.books.dao.BookDaoImpl;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест ДАО работы с книгами")
@DataJpaTest
@Import(BookDaoImpl.class)
public class BookDaoUnitTest {

	@Autowired
	private BookDaoImpl bookDao;

	@Autowired
	private TestEntityManager entityManager;

	@DisplayName("вставка новой книги и получение ее по ИД")
	@Test
	public void addBookGetByIdTest() {
		long authorId = 1;
		Author author = entityManager.find(Author.class, authorId);

		long genreId = 2;
		Genre genre = entityManager.find(Genre.class, genreId);

		Book book = new Book();
		String bookName = "Anna Karenina";
		book.setName(bookName);
		book.setAuthor(author);
		book.setGenre(genre);

		Comment comment1 = new Comment();
		Comment comment2 = new Comment();
		comment1.setText("Very booooooring book");
		comment2.setText("really depressive");
		comment1.setBook(book);
		comment2.setBook(book);
		entityManager.persist(comment1);
		entityManager.persist(comment2);

		long id = bookDao.add(book).getId();

		entityManager.clear();
		Book bookFromDb = bookDao.getAById(id).get();

		assertEquals(bookName, bookFromDb.getName());
		assertEquals(author, bookFromDb.getAuthor());
		assertEquals(genre, bookFromDb.getGenre());
		assertArrayEquals(new Comment[]{comment1, comment2}, bookFromDb.getComments().toArray());
	}

	@DisplayName("возвращает ИД всех книг")
	@Test
	public void getAllTest() {
		Long[] expected = {1L, 2L};

		List<Book> allBooks = bookDao.getAll();
		Long[] actual = allBooks.stream()
				.map(Book::getId)
				.toArray(Long[]::new);

		assertArrayEquals(expected, actual);
	}

	@DisplayName("изменение названия и автора")
	@Test
	public void updateBookTest() {
		long bookId = 1;
		Book book = bookDao.getAById(bookId).get();

		long newAuthorId = 5;
		Author newAuthor = entityManager.find(Author.class, newAuthorId);
		String newName = "I am in love with killer";
		Genre oldGenre = book.getGenre();

		bookDao.updateBook(book,newName, newAuthor, oldGenre);
		entityManager.detach(book);
		Book updatedBook = bookDao.getAById(bookId).get();

		assertEquals(newName, updatedBook.getName());
		assertEquals(newAuthor, updatedBook.getAuthor());
		assertEquals(oldGenre, updatedBook.getGenre());
	}

	@DisplayName("удаление книги")
	@Test
	public void deleteBookTest() {
		long id = 2;
		assertNotEquals(Optional.empty(), bookDao.getAById(id));

		bookDao.delete(id);
		entityManager.clear();

		assertEquals(Optional.empty(), bookDao.getAById(id));
	}
}
