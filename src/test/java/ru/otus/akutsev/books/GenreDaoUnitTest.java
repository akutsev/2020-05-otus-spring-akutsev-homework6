package ru.otus.akutsev.books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.akutsev.books.dao.GenreDao;
import ru.otus.akutsev.books.dao.GenreDaoImpl;
import ru.otus.akutsev.books.model.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест ДАО работы с жанрами")
@DataJpaTest
@Import(GenreDaoImpl.class)
public class GenreDaoUnitTest {

	@Autowired
	private GenreDao genreDao;

	@Autowired
	private TestEntityManager entityManager;

	@DisplayName("вставка нового жанра и получение его по ИД")
	@Test
	public void addGenreGetByIdTest() {
		String name = "Non fiction";
		Genre genre = new Genre();
		genre.setGenreName(name);

		long id = genreDao.add(genre).getId();

		entityManager.clear();
		Genre genreFromDb = genreDao.getAById(id).get();

		assertEquals(name, genreFromDb.getGenreName());
	}

	@DisplayName("изменение имени жанра")
	@Test
	public void updateGenreTest() {
		long id = 2;
		String newName = "Romance";
		Genre genre = entityManager.find(Genre.class, id);

		genreDao.updateGenreName(genre,newName);
		entityManager.detach(genre);
		Genre updatedGenre = entityManager.find(Genre.class, id);

		assertEquals(newName, updatedGenre.getGenreName());
	}

	@DisplayName("удаление жанра")
	@Test
	public void deleteGenreTest() {
		long id = 8;
		assertNotEquals(Optional.empty(), genreDao.getAById(id));

		genreDao.delete(id);
		entityManager.clear();

		assertEquals(Optional.empty(), genreDao.getAById(id));
	}
}
