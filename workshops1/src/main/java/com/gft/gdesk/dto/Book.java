package com.gft.gdesk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private long id;
    private String name;
    private String author;
    private int publishYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book that = (Book) o;

        return
                author == that.getAuthor() &&
                        name == that.getName() &&
                        publishYear == that.getPublishYear();

    }


    @Override
    public int hashCode() {
        return Objects.hash(this);
    }
}
