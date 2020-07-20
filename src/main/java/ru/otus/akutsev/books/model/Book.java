package ru.otus.akutsev.books.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
@NamedEntityGraph(name = "books-entity-graph", attributeNodes = {
		@NamedAttributeNode("author"),
		@NamedAttributeNode("genre"),
		@NamedAttributeNode("comments")
})
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "genre_id", nullable = false)
	private Genre genre;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private List<Comment> comments = new ArrayList<>();

	public Book(int id, String name, Author author, Genre genre) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.genre = genre;
	}

	public Book() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", author=" + author.getName() +
				", genre=" + genre.getGenreName() +
				", comments=" + comments.stream().map(comment -> comment.getText()).collect(Collectors.joining()) +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;
		Book book = (Book) o;
		return id == book.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
