package com.course.distributecommunication.books.models;

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
}
