package com.course.distributecommunication.books.controllers;

import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.models.BookAndAuthorDto;
import com.course.distributecommunication.books.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.val;
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
@RequiredArgsConstructor
@RequestMapping("books")
public class BookRestController {
    private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Collection<Book>> getAll() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Book> addBook(@RequestBody BookAndAuthorDto dto) {
        logger.info("Adding {}", dto);

        val newBook = bookService.add(dto);
        bookService.sendBookAndAuthorMessage(dto);

        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
}