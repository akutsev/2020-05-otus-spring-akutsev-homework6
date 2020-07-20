package ru.otus.akutsev.books.dao;

import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Book save(Book book) {
		return entityManager.merge(book);
	}

	@Override
	public Optional<Book> getAById(long id) {
		return Optional.ofNullable(entityManager.find(Book.class, id));
	}

	@Override
	public List<Book> getAll() {
		TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);

		EntityGraph<?> graph = entityManager.getEntityGraph("books-entity-graph");
		query.setHint("javax.persistence.fetchgraph", graph);

		return query.getResultList();
	}

	@Override
	public void delete (Book book) {
		entityManager.remove(book);
	}

}
