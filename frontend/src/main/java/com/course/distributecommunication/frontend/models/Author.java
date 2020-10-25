package com.course.distributecommunication.frontend.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {
    private final int id;
    @With
    private String firstName;
    @With
    private String lastName;
}
