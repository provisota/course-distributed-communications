package com.course.distributecommunication.frontend.services;

import com.course.distributecommunication.frontend.models.Aggregate;
import com.course.distributecommunication.frontend.models.Author;
import com.course.distributecommunication.frontend.models.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class DashboardRestService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardRestService.class);
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final static String AUTHORS_URL = "http://authors:8080/api/v1/authors";
    private final static String BOOKS_URL = "http://books:8080/api/v1/books";

    public Aggregate getAggregate() {
        val authors = getAuthors();
        val books = getBooks();
        return new Aggregate(authors, books);
    }

    private Collection<Book> getBooks() {
        val booksJson = restTemplate.getForEntity(BOOKS_URL, String.class);

        try {
            return objectMapper.readValue(
                    booksJson.getBody(),
                    new TypeReference<Collection<Book>>() {
                    }
            );
        } catch (IOException e) {
            logger.error("Could not deserialize Collection<Book>", e);
        }

        return Collections.emptyList();
    }

    private Collection<Author> getAuthors() {
        val authorsResponse = restTemplate.getForEntity(AUTHORS_URL, String.class);

        try {
            return objectMapper.readValue(
                    authorsResponse.getBody(),
                    new TypeReference<Collection<Author>>() {
                    }
            );
        } catch (IOException e) {
            logger.error("Could not deserialize Collection<Author>", e);
        }

        return Collections.emptyList();
    }

}

