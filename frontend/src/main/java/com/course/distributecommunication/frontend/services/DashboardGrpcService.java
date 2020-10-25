package com.course.distributecommunication.frontend.services;

import com.course.distributecommunication.frontend.models.Aggregate;
import com.course.distributecommunication.frontend.models.Author;
import com.course.distributecommunication.frontend.models.Book;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class DashboardGrpcService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardGrpcService.class);

    public Aggregate getAggregate() {
        val authors = getAuthors();
        val books = getBooks();
        return new Aggregate(authors, books);
    }

    private Collection<Book> getBooks() {
        // TODO

        return Collections.emptyList();
    }

    private Collection<Author> getAuthors() {
        // TODO

        return Collections.emptyList();
    }
}

