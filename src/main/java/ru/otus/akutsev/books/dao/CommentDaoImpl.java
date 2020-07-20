package ru.otus.akutsev.books.dao;

import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;
import ru.otus.akutsev.books.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Comment save(Comment comment) {
		return entityManager.merge(comment);
	}

	@Override
	public Optional<Comment> getAById(long id) {
		return Optional.ofNullable(entityManager.find(Comment.class, id));
	}


	@Override
	public List<Comment> getAllComments(Book book) {
		Query query = entityManager.createQuery("select c from Comment c where c.book = :book", Comment.class);
		query.setParameter("book", book);
		return query.getResultList();
	}

	@Override
	public void delete(Comment comment) {
		entityManager.remove(comment);
	}
}
