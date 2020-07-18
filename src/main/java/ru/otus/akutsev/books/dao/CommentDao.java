package ru.otus.akutsev.books.dao;

import ru.otus.akutsev.books.model.Comment;

import java.util.Optional;

public interface CommentDao {

	public Comment add(Comment comment);
	public Optional<Comment> getAById(long id);
	public void updateCommentText(Comment comment, String newText);
	public void delete (long id);

}
