package ru.otus.akutsev.books.dao;

import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Author save(Author author) {
		return entityManager.merge(author);
	}

	@Override
	public Optional<Author> getAById(long id) {
		return Optional.ofNullable(entityManager.find(Author.class, id));
	}

	@Override
	public void delete (Author author) {
		entityManager.remove(author);
	}

}
