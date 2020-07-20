package ru.otus.akutsev.books.dao;

import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

	Comment save(Comment comment);
	Optional<Comment> getAById(long id);
	void delete (Comment comment);
	List<Comment> getAllComments(Book book);

}
