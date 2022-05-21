package com.gft.gdesk;

import com.gft.gdesk.dto.Book;
import com.gft.gdesk.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceTest {
    BookService bookservice;

    @BeforeEach
    public void initBookservice() {
        bookservice = new BookService();
        bookservice.setInitialBooks();

    }

    @Test
    public void addNewProperBookTest() {
        //given
        Integer expectedSize = 3;
        Book book = Book.builder().
                id(2L).
                author("Jan").
                name("Kowalski").
                publishYear(1999).
                build();

        //when
        bookservice.addBook(book);

        //then
        Integer actualSize = bookservice.getSize();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void addBookWithExistingIdTest() {
        //given
        Book book = Book.builder().
                id(0L).
                author("Sapkowski").
                name("Wiedzmin").
                publishYear(1999).
                build();

        //when
        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            bookservice.addBook(book);
        }, "ResponseStatusException was expected");

        // then
        Integer expectedSize = 2;
        Integer actualSize = bookservice.getSize();
        assertEquals(expectedSize, actualSize);

        HttpStatus expectedErrorStatus = HttpStatus.BAD_REQUEST;
        HttpStatus actualErrorStatus = thrown.getStatus();
        assertEquals(expectedErrorStatus, actualErrorStatus);
    }
    
    @Test
    public void removeBookTest1(){
        //given
        Book bookToBeRemoved = bookservice.getAllBooks().get(0);
        Book excpetedBook = bookservice.getAllBooks().get(1);

        //when
        bookservice.removeBook(bookToBeRemoved.getId());

        //then
        Integer expectedSize = 1;
        Integer actualSize = bookservice.getSize();
        assertEquals(expectedSize, actualSize);

        Book actualBook = bookservice.getAllBooks().get(0);
        assertEquals(excpetedBook, actualBook);
    }

    @Test
    public void updateBook(){
        //given
        Book expectedBook = bookservice.getAllBooks().get(0);
        expectedBook.setAuthor("Zmieniony autor");

        //when
        bookservice.updateBook(expectedBook);

        //then
        Book actualBook = bookservice.getAllBooks().get(0);
        assertEquals(expectedBook, actualBook);
    }
}
