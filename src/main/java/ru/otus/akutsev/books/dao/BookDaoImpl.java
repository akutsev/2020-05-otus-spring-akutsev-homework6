package ru.otus.akutsev.books.dao;

import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Book add(Book book) {
//		if (book.getId() == 0) {
//			entityManager.persist(book);
//			return book;
//		} else {
//			return entityManager.merge(book);
//		}
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
	public void updateBook (Book book, String newName, Author newAuthor, Genre newGenre) {
		Query query = entityManager.createQuery("update Book b set b.name = :name, b.author = :author," +
				"b.genre = :genre where b.id = :id");
		query.setParameter("name", newName);
		query.setParameter("author", newAuthor);
		query.setParameter("genre", newGenre);
		query.setParameter("id", book.getId());
		query.executeUpdate();
	}

	@Override
	public void delete (long id) {
		Query query = entityManager.createQuery("delete from Book s where s.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
