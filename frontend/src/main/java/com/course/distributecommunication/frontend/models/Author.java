package com.course.distributecommunication.frontend.models;

import com.course.distributecommunication.grpc.authors.AuthorDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {
    private final int id;
    @With
    private String firstName;
    @With
    private String lastName;

    public static Author fromDto(AuthorDto dto) {
        return Author.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }
}
