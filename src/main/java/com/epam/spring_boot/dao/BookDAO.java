package com.epam.spring_boot.dao;

import com.epam.spring_boot.dto.BookDTO;
import com.epam.spring_boot.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BookDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Book> getBooks(String search){
        String sql = "select * from test_book";
        List<Book> books = namedParameterJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
        if (search != null && !search.isEmpty()){
            return books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(search)
                    || book.getTitle().equalsIgnoreCase(search) || book.getDescription().equalsIgnoreCase(search)
            ).toList();
        }else return books;
    }

    public Integer addBook(BookDTO dto){
        String sql = "insert into test_book(title, author, price, description) values (:title,:author,:price,:description);";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", dto.getTitle())
                        .addValue("author", dto.getAuthor())
                                .addValue("price", dto.getPrice())
                                        .addValue("description", dto.getDescription());

        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public Integer deleteBook(Long id) {
        String sql = "delete from test_book where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public Optional<Book> getBookById(Long id) {
        String sql = "select * from test_book where id = :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        try {
            Book book = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, BeanPropertyRowMapper.newInstance(Book.class));
            return Optional.of(book);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Integer updateBook(BookDTO dto, Long id) {
        String sql = "update test_book set title = :title, author = :author, price = :price, description = :description where id = :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id)
                .addValue("author", dto.getAuthor())
                .addValue("title", dto.getTitle())
                .addValue("price", dto.getPrice())
                .addValue("description", dto.getDescription());
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
