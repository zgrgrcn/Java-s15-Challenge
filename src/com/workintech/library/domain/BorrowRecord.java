package com.workintech.library.domain;

import java.time.LocalDate;

public class BorrowRecord {
    private final Book book;
    private final Reader reader;
    private final LocalDate borrowDate = LocalDate.now();
    private boolean returned;

    public BorrowRecord(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markReturned() {
        this.returned = true;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }
}
