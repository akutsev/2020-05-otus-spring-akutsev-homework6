package ru.otus.akutsev.books.dao;

import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Comment add(Comment comment) {
		if (comment.getId() == 0) {
			entityManager.persist(comment);
			return comment;
		} else {
			return entityManager.merge(comment);
		}
	}

	@Override
	public Optional<Comment> getAById(long id) {
		return Optional.ofNullable(entityManager.find(Comment.class, id));
	}

	@Override
	public void updateCommentText(Comment comment, String newText) {
		Query query = entityManager.createQuery("update Comment c set c.text = :text where c.id = :id");
		query.setParameter("id", comment.getId());
		query.setParameter("text", newText);
		query.executeUpdate();
	}

	@Override
	public void delete(long id) {
		Query query = entityManager.createQuery("delete from Comment c where c.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
