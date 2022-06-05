package com.gft.gdesk.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void setInitialBooks() {
        this.books.addAll(Arrays.asList(
                Book.builder().
                        id(0L).
                        author("Sapkowski").
                        name("Wiedzmin").
                        publishYear(1999).
                        build(),
                Book.builder().
                        id(1L).
                        author("J.K Rowling").
                        name("Harry Potter").
                        publishYear(1990).
                        build()
        ));
    }


    public List<Book> getAllBooks() {
        return this.books;
    }

    public void addBook(Book book) {
        if (books.contains(book))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        books.add(book);
    }

    public void removeBook(long id) {
        books.removeIf(item -> item.getId() == id);
    }

    public Book updateBook(Book book) {
        Optional<Book> checkBook = checkIfBookExists(book.getId());
        if (checkBook.isPresent()) {
            Book toUpdate = checkBook.get();
            toUpdate.setAuthor(book.getAuthor());
            toUpdate.setName(book.getName());
            toUpdate.setPublishYear(book.getPublishYear());
            books.set(books.indexOf(book), toUpdate);
            return toUpdate;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Integer getSize() {
        return books.size();
    }

    private Optional<Book> checkIfBookExists(long id) {
        return books.stream()
                .filter(x -> x.getId() == id)
                .findAny();
    }

}
