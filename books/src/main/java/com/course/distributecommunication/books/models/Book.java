package com.course.distributecommunication.books.models;

import com.course.distributecommunication.grpc.books.BookDto;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
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
