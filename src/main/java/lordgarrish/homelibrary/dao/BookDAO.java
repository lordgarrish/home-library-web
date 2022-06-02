package lordgarrish.homelibrary.dao;

import lordgarrish.homelibrary.business.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        String query = "SELECT book_id, title, first_name, last_name, genre, year FROM books " +
                "JOIN authors USING(author_id) JOIN genres USING(genre_id)";

        return jdbcTemplate.query(query, new BookMapper());
    }

    public Book getById(int id) {
        String query = "SELECT book_id, title, first_name, last_name, genre, year FROM books " +
                "JOIN authors USING(author_id) JOIN genres USING(genre_id) WHERE book_id=?";

        return jdbcTemplate.query(query, new BookMapper(), id)
                .stream().findAny().orElseGet(Book::new);
    }

    public void update(int id, Book book) {
        String updateAuthor = "UPDATE authors SET first_name=?, last_name=? WHERE author_id=" +
                "(SELECT author_id FROM books WHERE book_id=?)";
        String updateGenre = "UPDATE genres SET genre=? WHERE genre_id=" +
                "(SELECT genre_id FROM books WHERE book_id=?)";
        String updateBook = "UPDATE books SET title=?, year=? WHERE book_id=?";

        String[] authorName = book.getAuthor().split(" ");

        jdbcTemplate.update(updateAuthor, authorName[0], authorName[1], id);
        jdbcTemplate.update(updateGenre, book.getGenre(), id);
        jdbcTemplate.update(updateBook, book.getTitle(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE book_id=?", id);
    }

    public void save(Book book) {
        String insertAuthor = "INSERT IGNORE INTO authors (first_name, last_name) VALUES (?, ?)";
        String insertGenre = "INSERT IGNORE INTO genres (genre) VALUES (?)";
        String insertBook = "INSERT INTO books (title, author_id, genre_id, year) VALUES " +
                "(?, (SELECT author_id FROM authors WHERE first_name = ? AND last_name = ?), " +
                "(SELECT genre_id FROM genres WHERE genre = ?), ?)";

        String[] authorName = book.getAuthor().split(" ");

        jdbcTemplate.update(insertAuthor, authorName[0], authorName[1]);
        jdbcTemplate.update(insertGenre, book.getGenre());
        jdbcTemplate.update(insertBook, book.getTitle(), authorName[0], authorName[1], book.getGenre(), book.getYear());
    }
}
