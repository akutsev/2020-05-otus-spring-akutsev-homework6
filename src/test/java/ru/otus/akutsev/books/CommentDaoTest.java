package ru.otus.akutsev.books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.akutsev.books.dao.CommentDao;
import ru.otus.akutsev.books.dao.CommentDaoImpl;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Тест ДАО работы с комментариями")
@DataJpaTest
@Import(CommentDaoImpl.class)
public class CommentDaoTest {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private TestEntityManager entityManager;

	@DisplayName("вставка нового комментария и получение его по ИД")
	@Test
	public void addCommentGetByIdTest() {
		long bookId = 1;
		Book book = entityManager.find(Book.class, bookId);

		String text = "Very booooooring book";
		Comment comment = new Comment();
		comment.setText(text);
		comment.setBook(book);

		long id = commentDao.add(comment).getId();

		entityManager.clear();
		Comment commentFromDb = commentDao.getAById(id).get();

		assertEquals(text, commentFromDb.getText());
		assertEquals(book, commentFromDb.getBook());
	}

	@DisplayName("изменение текста комментария")
	@Test
	public void updateCommentTest() {
		long id = 2;
		String newText = "I'm sorry, this book worth 4 stars!";
		Comment comment = entityManager.find(Comment.class, id);

		commentDao.updateCommentText(comment,newText);
		entityManager.detach(comment);
		Comment updatedComment = entityManager.find(Comment.class, id);

		assertEquals(newText, updatedComment.getText());
	}

	@DisplayName("удаление комментария")
	@Test
	public void deleteGenreTest() {
		long id = 4;
		assertNotEquals(Optional.empty(), commentDao.getAById(id));

		commentDao.delete(id);
		entityManager.clear();

		assertEquals(Optional.empty(), commentDao.getAById(id));
	}
}
