package ru.otus.akutsev.books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;
import ru.otus.akutsev.books.service.BookService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Тест приложения с поднятием контекста")
@SpringBootTest
public class BookServiceIntegrationTest {

	@Autowired
	BookService bookService;

	@DisplayName("на примере добавления+извлечения книги")
	@Test
	public void addBookGetByIdTest() {
		String authorName = "Lev Tolstoy";
		Author author = new Author();
		author.setName(authorName);

		String genreName = "Drama";
		Genre genre = new Genre();
		genre.setGenreName(genreName);

		String bookName = "Anna Karenina";
		Book book = new Book();
		book.setName(bookName);
		book.setAuthor(author);
		book.setGenre(genre);

		assertFalse(bookService.getAll().contains(book));

		long id = bookService.add(book).getId();

		Book bookFromDb = bookService.getAById(id).get();
		assertEquals(bookName, bookFromDb.getName());
		assertEquals(author.getName(), bookFromDb.getAuthor().getName());
		assertEquals(genre.getGenreName(), bookFromDb.getGenre().getGenreName());
	}

}
