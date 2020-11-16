package com.course.distributecommunication.authors.models;

import com.course.distributecommunication.grpc.authors.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.With;

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

    public AuthorDto asAuthorDto() {
        return AuthorDto.newBuilder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }

    public static Author fromDto(AuthorDto dto) {
        return Author.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }
}
