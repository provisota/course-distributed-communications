package com.course.distributecommunication.authors.controllers;

import com.course.distributecommunication.authors.models.Author;
import com.course.distributecommunication.authors.services.AuthorService;
import com.course.distributecommunication.grpc.authors.AddAuthorIfNotExistRequest;
import com.course.distributecommunication.grpc.authors.AddAuthorIfNotExistResponse;
import com.course.distributecommunication.grpc.authors.AuthorsServiceGrpc;
import com.course.distributecommunication.grpc.authors.GetAllAuthorsRequest;
import com.course.distributecommunication.grpc.authors.GetAllAuthorsResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@GrpcService
public class AuthorGrpcController extends AuthorsServiceGrpc.AuthorsServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(AuthorGrpcController.class);

    private final AuthorService authorService;

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

    @Override
    public void addAuthorIfNotExist(AddAuthorIfNotExistRequest request, StreamObserver<AddAuthorIfNotExistResponse> responseObserver) {
        val dto = request.getAuthor();

        val isAuthorExist = authorService.isExist(dto.getId());

        if (!isAuthorExist) {
            authorService.add(Author.fromDto(dto));
        }

        val response = AddAuthorIfNotExistResponse.newBuilder()
                .setAuthorHasAdded(!isAuthorExist)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
