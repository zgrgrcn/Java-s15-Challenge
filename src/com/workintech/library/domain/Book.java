package com.workintech.library.domain;

import java.time.LocalDate;

public class Book {
    private final int id;
    private String title;
    private Author author;
    private Category category;
    private LocalDate dateOfPurchase = LocalDate.now();
    private BookStatus status = BookStatus.AVAILABLE;

    public Book(int id, String title, Author author, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    public void markBorrowed() {
        status = BookStatus.BORROWED;
    }

    public void markReturned() {
        status = BookStatus.AVAILABLE;
    }

    public void update(String title, Author author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", status=" + status +
                '}';
    }
}
