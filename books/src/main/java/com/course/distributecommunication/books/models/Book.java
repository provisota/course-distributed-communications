package com.course.distributecommunication.books.models;

import com.course.distributecommunication.books.grpc.BookDto;
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

    public BookDto asBookDto() {
        return BookDto.newBuilder()
                .setId(id)
                .setTitle(title)
                .setPages(pages)
                .setAuthorId(authorId)
                .build();
    }
}
