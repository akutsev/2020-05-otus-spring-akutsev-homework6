package ru.otus.akutsev.books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.akutsev.books.dao.AuthorDao;
import ru.otus.akutsev.books.dao.AuthorDaoImpl;
import ru.otus.akutsev.books.model.Author;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест ДАО работы с авторами")
@DataJpaTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoUnitTest {

	@Autowired
	private AuthorDao authorDao;

	@Autowired
	private TestEntityManager entityManager;

	@DisplayName("вставка нового автора и получение его по ИД")
	@Test
	public void addAuthorGetByIdTest() {
		String name = "Aleksandr Pushkin";
		Author author = new Author();
		author.setName(name);

		long id = authorDao.save(author).getId();

		entityManager.clear();
		Author authorFromDb = authorDao.getAById(id).get();

		assertEquals(name, authorFromDb.getName());
	}

	@DisplayName("удаление автора")
	@Test
	public void deleteAuthorTest() {
		long id = 5;
		Author authorToDelete = entityManager.find(Author.class, id);

		assertNotEquals(Optional.empty(), authorDao.getAById(id));
		authorDao.delete(authorToDelete);
		assertEquals(Optional.empty(), authorDao.getAById(id));
	}

}
