package com.course.distributecommunication.authors.services;

import com.course.distributecommunication.authors.models.Author;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class AuthorService {
    private final HashMap<Integer, Author> authors;

    public AuthorService() {
        authors = new HashMap<>();
        authors.put(1, new Author(1).withFirstName("Loreth Anne").withLastName("White - v2"));
        authors.put(2, new Author(2).withFirstName("Lisa").withLastName("Regan - v2"));
        authors.put(3, new Author(3).withFirstName("Ty").withLastName("Patterson - v2"));
    }

    public Collection<Author> getAuthors() {
        return authors.values();
    }

    public Author findById(int id) {
        return authors.get(id);
    }


    public boolean isExist(int id) {
        return authors.get(id) != null;
    }

    public void add(Author author) {
        authors.put(author.getId(), author);
    }
}
