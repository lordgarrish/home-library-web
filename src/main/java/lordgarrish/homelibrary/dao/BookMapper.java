package lordgarrish.homelibrary.dao;

import lordgarrish.homelibrary.business.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("first_name") + " " + rs.getString("last_name"));
        book.setGenre(rs.getString("genre"));
        book.setYear(rs.getInt("year"));

        return book;
    }
}
