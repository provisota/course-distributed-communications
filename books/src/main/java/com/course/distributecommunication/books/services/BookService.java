package com.course.distributecommunication.books.services;

import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.models.BookAndAuthorDto;
import lombok.val;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import static com.course.distributecommunication.rabbit.Constants.BOOK_AND_AUTHOR_QUEUE;

@Service
public class BookService {
    private final HashMap<Integer, Book> books;
    private final RabbitTemplate rabbitTemplate;

    public BookService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        books = new HashMap<>();
        books.put(1, new Book(1).withTitle("Semiosis: A Novel - v2").withPages(326).withAuthorId(1));
        books.put(2, new Book(2).withTitle("The Loosening Skin - v2").withPages(132).withAuthorId(1));
        books.put(3, new Book(3).withTitle("Ninefox Gambit - v2").withPages(384).withAuthorId(2));
        books.put(4, new Book(4).withTitle("Raven Stratagem - v2").withPages(400).withAuthorId(3));
        books.put(5, new Book(5).withTitle("Revenant Gun - v2").withPages(466).withAuthorId(3));
    }

    public Collection<Book> getBooks() {
        return this.books.values();
    }

    public Book findById(int id) {
        return this.books.get(id);
    }

    public Book add(BookAndAuthorDto dto) {
        val lastId = books.keySet()
                .stream()
                .max(Comparator.comparingInt(left -> left))
                .orElse(1);

        Book newBook = Book.builder()
                .id(lastId + 1)
                .title(dto.getTitle())
                .pages(dto.getPages())
                .authorId(dto.getAuthorId())
                .build();

        books.put(newBook.getId(), newBook);

        return newBook;
    }

    public void sendBookAndAuthorMessage(BookAndAuthorDto dto) {
        rabbitTemplate.convertAndSend(BOOK_AND_AUTHOR_QUEUE, dto.toMessage());
    }
}
