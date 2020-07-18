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
	public Genre add(Genre genre) {
		if (genre.getId() == 0) {
			entityManager.persist(genre);
			return genre;
		} else {
			return entityManager.merge(genre);
		}
	}

	@Override
	public Optional<Genre> getAById(long id) {
		return Optional.ofNullable(entityManager.find(Genre.class, id));
	}

	@Override
	public void updateGenreName(Genre genre, String newName) {
		Query query = entityManager.createQuery("update Genre g set g.genreName = :name where g.id = :id");
		query.setParameter("id", genre.getId());
		query.setParameter("name", newName);
		query.executeUpdate();
	}

	@Override
	public void delete (long id) {
		Query query = entityManager.createQuery("delete from Genre g where g.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
