package com.gft.gdesk.controller;

import com.gft.gdesk.dto.Book;
import com.gft.gdesk.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController

public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
}
