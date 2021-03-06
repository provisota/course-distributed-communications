package com.course.distributecommunication.authors.controllers;

import com.course.distributecommunication.authors.models.Author;
import com.course.distributecommunication.authors.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequiredArgsConstructor
@Controller
@RequestMapping("authors")
public class AuthorRestController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorRestController.class);

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<Collection<Author>> getAll() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK) ;
    }

}
