package com.course.distributecommunication.books.services;

import java.util.Collection;
import java.util.HashMap;

import com.course.distributecommunication.books.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookService
{
    private final HashMap<Integer, Book> books;

    public BookService() {
        books = new HashMap<Integer, Book>();
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
}
