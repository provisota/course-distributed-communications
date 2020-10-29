package com.course.distributecommunication.books.models;

import com.course.distributecommunication.rabbit.BookAndAuthorMessage;
import lombok.*;

/**
 * @author Ilya Alterovych
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookAndAuthorDto {
    private String title;
    private int pages;
    private int authorId;
    private String firstName;
    private String lastName;

    public BookAndAuthorMessage toMessage() {
        return new BookAndAuthorMessage(
                title,
                pages,
                authorId,
                firstName,
                lastName
        );
    }
}
