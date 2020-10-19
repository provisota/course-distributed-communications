package com.course.distributecommunication.books.controlles;

import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Collection<Book>> getAll() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }
}