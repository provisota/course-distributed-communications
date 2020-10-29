package com.course.distributecommunication.rabbit;

import lombok.*;

import java.io.Serializable;

/**
 * @author Ilya Alterovych
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookAndAuthorMessage implements Serializable {
    private String title;
    private int pages;
    private int authorId;
    private String firstName;
    private String lastName;
}
