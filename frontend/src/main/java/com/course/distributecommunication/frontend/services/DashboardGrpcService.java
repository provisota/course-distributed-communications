package com.course.distributecommunication.frontend.services;

import com.course.distributecommunication.authors.grpc.AuthorsServiceGrpc;
import com.course.distributecommunication.authors.grpc.GetAllAuthorsRequest;
import com.course.distributecommunication.books.grpc.BooksServiceGrpc;
import com.course.distributecommunication.books.grpc.GetAllBooksRequest;
import com.course.distributecommunication.frontend.models.Aggregate;
import com.course.distributecommunication.frontend.models.Author;
import com.course.distributecommunication.frontend.models.Book;
import lombok.val;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class DashboardGrpcService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardGrpcService.class);

    @GrpcClient("books")
    private BooksServiceGrpc.BooksServiceBlockingStub booksClient;

    @GrpcClient("authors")
    private AuthorsServiceGrpc.AuthorsServiceBlockingStub authorsClient;

    public Aggregate getAggregate() {
        val authors = getAuthors();
        val books = getBooks();
        return new Aggregate(authors, books);
    }

    private Collection<Book> getBooks() {
        val books = booksClient.getAll(GetAllBooksRequest.newBuilder().build());

        return books.getBooksList().stream()
                .map(Book::fromDto)
                .collect(Collectors.toList());
    }

    private Collection<Author> getAuthors() {
        val authors = authorsClient.getAll(GetAllAuthorsRequest.newBuilder().build());

        return authors.getAuthorsList().stream()
                .map(Author::fromDto)
                .collect(Collectors.toList());
    }
}

