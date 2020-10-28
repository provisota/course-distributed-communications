package com.course.distributecommunication.books.controllers;

import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.models.BookAndAuthorDto;
import com.course.distributecommunication.books.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("books")
public class BookRestController {
    private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);

    final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Collection<Book>> getAll() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Book> addBook(@RequestBody BookAndAuthorDto dto) {
        logger.info("Adding {}", dto);
        return new ResponseEntity<>(bookService.add(dto), HttpStatus.CREATED);
    }
}