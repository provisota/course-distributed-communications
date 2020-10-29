package com.course.distributecommunication.authors.models;

import com.course.distributecommunication.authors.grpc.AuthorDto;
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

    public AuthorDto asAuthorDto() {
        return AuthorDto.newBuilder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }
}
