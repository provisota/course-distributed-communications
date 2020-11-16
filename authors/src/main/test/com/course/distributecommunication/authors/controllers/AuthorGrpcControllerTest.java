package com.course.distributecommunication.authors.controllers;

import com.course.distributecommunication.grpc.authors.AuthorDto;
import com.course.distributecommunication.grpc.authors.AuthorsServiceGrpc;
import com.course.distributecommunication.grpc.authors.GetAllAuthorsRequest;
import com.course.distributecommunication.grpc.authors.GetAllAuthorsResponse;
import com.course.distributecommunication.authors.services.AuthorService;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import lombok.val;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author Ilya Alterovych
 */
@RunWith(JUnit4.class)
public class AuthorGrpcControllerTest {
    /**
     * This rule manages automatic graceful shutdown for the registered servers and channels at the
     * end of test.
     */
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    /**
     * To test the server, make calls with a real stub using the in-process channel, and verify
     * behaviors or state changes from the client side.
     */
    @Test
    public void getAll() throws Exception {
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();

        // Create a server, add service, start, and register for automatic graceful shutdown.
        val authorService = new AuthorService();

        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(
                        new AuthorGrpcController(authorService)).build().start()
        );

        AuthorsServiceGrpc.AuthorsServiceBlockingStub blockingStub = AuthorsServiceGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );


        GetAllAuthorsResponse response = blockingStub.getAll(GetAllAuthorsRequest.newBuilder().build());

        val expectedAuthorsList = authorService.getAuthors().stream().map(author ->
                AuthorDto.newBuilder()
                        .setId(author.getId())
                        .setFirstName(author.getFirstName())
                        .setLastName(author.getLastName())
                        .build()
        ).collect(Collectors.toList());

        assertEquals( String.format("Authors count should be %s", expectedAuthorsList.size()), 3, response.getAuthorsCount());
        assertEquals(expectedAuthorsList, response.getAuthorsList());
    }
}
