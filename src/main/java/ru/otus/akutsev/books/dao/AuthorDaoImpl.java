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
	public Author add(Author author) {
		if (author.getId() == 0) {
			entityManager.persist(author);
			return author;
		} else {
			return entityManager.merge(author);
		}
	}

	@Override
	public Optional<Author> getAById(long id) {
		return Optional.ofNullable(entityManager.find(Author.class, id));
	}

	@Override
	public void updateName(Author author, String newName) {
		Query query = entityManager.createQuery("update Author a set a.name = :name where a.id = :id");
		query.setParameter("id", author.getId());
		query.setParameter("name", newName);
		query.executeUpdate();
	}

	@Override
	public void delete (long id) {
		Query query = entityManager.createQuery("delete from Author a where a.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
