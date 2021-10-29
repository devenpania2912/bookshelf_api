package com.example.Bookshelf.book;

import org.springframework.data.annotation.Id;

public class Book {

    private final Long id;
    private final String author;
    private final int publish_year;
    private final String image_url;
    private final String is_finished;
    private final int rating;

    public Book(
            Long id,
            String author,
            int publish_year,
            String image_url,
            String is_finished,
            int rating
    ) {
        this.id = id;
        this.author = author;
        this.publish_year = publish_year;
        this.image_url = image_url;
        this.is_finished = is_finished;
        this.rating=rating;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishYear() {
        return publish_year;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getIsFinished() {
        return is_finished;
    }

    public int getRating() {
        return rating;
    }

    public Book updateWith(Book Book) {
        return new Book(
            this.id,
            Book.author,
            Book.publish_year,
            Book.image_url,
            Book.is_finished,
            Book.rating
        );
    }
}