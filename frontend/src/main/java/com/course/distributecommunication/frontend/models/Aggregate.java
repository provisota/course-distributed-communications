package com.course.distributecommunication.frontend.models;

import lombok.*;

import java.util.Collection;

@Value
@AllArgsConstructor
public class Aggregate {
    Collection<Author> authors;
    Collection<Book> books;
}
