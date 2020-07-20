package ru.otus.akutsev.books.dao;

import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Genre save(Genre genre) {
		return entityManager.merge(genre);
	}

	@Override
	public Optional<Genre> getAById(long id) {
		return Optional.ofNullable(entityManager.find(Genre.class, id));
	}

	@Override
	public void delete (Genre genre) {
		entityManager.remove(genre);
	}
}
