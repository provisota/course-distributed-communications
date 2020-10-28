package com.course.distributecommunication.authors.controllers;

import com.course.distributecommunication.authors.grpc.AuthorsServiceGrpc;
import com.course.distributecommunication.authors.grpc.GetAllAuthorsRequest;
import com.course.distributecommunication.authors.grpc.GetAllAuthorsResponse;
import com.course.distributecommunication.authors.models.Author;
import com.course.distributecommunication.authors.services.AuthorService;
import io.grpc.stub.StreamObserver;
import lombok.val;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@GrpcService
public class AuthorGrpcController extends AuthorsServiceGrpc.AuthorsServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(AuthorGrpcController.class);

    final AuthorService authorService;

    public AuthorGrpcController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void getAll(GetAllAuthorsRequest request, StreamObserver<GetAllAuthorsResponse> responseObserver) {
        val authors = authorService.getAuthors();

        val response = GetAllAuthorsResponse.newBuilder()
                .addAllAuthors(
                        authors.stream().map(Author::asAuthorDto).collect(Collectors.toList())
                ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
