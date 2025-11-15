package com.workintech.library.domain;

public class Invoice {
    private final int id;
    private final Reader reader;
    private final Book book;
    private final double amount;
    private boolean refunded;

    public Invoice(int id, Reader reader, Book book, double amount) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.amount = amount;
    }

    public void refund() {
        this.refunded = true;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return book.getId();
    }

    public boolean isRefunded() {
        return refunded;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", reader=" + reader.getName() +
                ", book=" + book.getTitle() +
                ", amount=" + amount +
                ", refunded=" + refunded +
                '}';
    }
}
