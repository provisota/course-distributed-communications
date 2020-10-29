package com.course.distributecommunication.books.controllers;

import com.course.distributecommunication.grpc.books.BooksServiceGrpc;
import com.course.distributecommunication.grpc.books.GetAllBooksRequest;
import com.course.distributecommunication.grpc.books.GetAllBooksResponse;
import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.services.BookService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@GrpcService
public class BookGrpcController extends BooksServiceGrpc.BooksServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(BookGrpcController.class);

    final BookService bookService;

    @Override
    public void getAll(GetAllBooksRequest request, StreamObserver<GetAllBooksResponse> responseObserver) {
        val books = bookService.getBooks();

        val response = GetAllBooksResponse.newBuilder()
                .addAllBooks(
                        books.stream().map(Book::asBookDto).collect(Collectors.toList())
                ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
