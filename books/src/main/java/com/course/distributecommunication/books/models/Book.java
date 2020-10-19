package com.course.distributecommunication.books.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {
    private final int id;
    @With
    private String title;
    @With
    private int pages;
    @With
    private int authorId;
}
