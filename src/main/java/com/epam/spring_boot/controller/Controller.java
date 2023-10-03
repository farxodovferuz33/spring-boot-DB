package com.epam.spring_boot.controller;

import com.epam.spring_boot.dao.BookDAO;
import com.epam.spring_boot.dto.BookDTO;
import com.epam.spring_boot.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {


    private final BookDAO bookDAO;

    public Controller(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) String search){
//        if (search != null && !search.isEmpty()) {
//            String sql = "select * from test_book where title = :search or author= :search or description = :search";
//            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//            parameterSource.addValue("search", search);
//            List<Book> books = namedParameterJdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(Book.class));
//            model.addAttribute("books", books);
//        }else {}
        model.addAttribute("books",bookDAO.getBooks(search));
        return "views/home";
    }

    @GetMapping("/create/book")
    public String createBookPage(){
        return "views/book_create";
    }

    @PostMapping("/create/book")
    public String createBook(@ModelAttribute BookDTO dto) {
        bookDAO.addBook(dto);
        return "redirect:/";
    }

    @PostMapping("/delete/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookDAO.deleteBook(id);
        return "redirect:/";
    }

    @GetMapping("/update/book/{id}")
    public String updateBookPage(@PathVariable Long id, Model model) {
        bookDAO.getBookById(id).ifPresent(book -> model.addAttribute("book", book));
        return "views/book_update";
    }

    @PostMapping("/update/book/{id}")
    public String updateBook(@ModelAttribute BookDTO dto, @PathVariable Long id) {
        bookDAO.updateBook(dto, id);
        return "redirect:/";
    }


}
