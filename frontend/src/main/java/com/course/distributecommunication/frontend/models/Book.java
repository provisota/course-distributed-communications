package com.course.distributecommunication.frontend.models;

import com.course.distributecommunication.books.grpc.BookDto;
import lombok.*;

@Getter
@Setter
@Builder
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

    public static Book fromDto(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .pages(dto.getPages())
                .authorId(dto.getAuthorId())
                .build();
    }
}
