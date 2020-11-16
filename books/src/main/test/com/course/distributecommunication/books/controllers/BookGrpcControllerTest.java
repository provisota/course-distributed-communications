package com.course.distributecommunication.books.controllers;

import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.services.BookService;
import com.course.distributecommunication.grpc.books.BooksServiceGrpc;
import com.course.distributecommunication.grpc.books.GetAllBooksRequest;
import com.course.distributecommunication.grpc.books.GetAllBooksResponse;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import lombok.val;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author Ilya Alterovych
 */
@RunWith(MockitoJUnitRunner.class)
public class BookGrpcControllerTest {
    /**
     * This rule manages automatic graceful shutdown for the registered servers and channels at the
     * end of test.
     */
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Mock
    private RabbitTemplate rabbitTemplate;

    /**
     * To test the server, make calls with a real stub using the in-process channel, and verify
     * behaviors or state changes from the client side.
     */
    @Test
    public void getAll() throws Exception {
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();

        // Create a server, add service, start, and register for automatic graceful shutdown.
        val bookService = new BookService(rabbitTemplate);

        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(
                        new BookGrpcController(bookService)).build().start()
        );

        BooksServiceGrpc.BooksServiceBlockingStub blockingStub = BooksServiceGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );


        GetAllBooksResponse response = blockingStub.getAll(GetAllBooksRequest.newBuilder().build());

        val expectedBooksList = bookService.getBooks().stream().map(Book::asBookDto).collect(Collectors.toList());

        assertEquals(String.format("Books count should be %s", expectedBooksList.size()), 5, response.getBooksCount());
        assertEquals(expectedBooksList, response.getBooksList());
    }
}
