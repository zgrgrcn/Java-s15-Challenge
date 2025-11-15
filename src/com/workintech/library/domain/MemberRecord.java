package com.workintech.library.domain;

import java.time.LocalDate;

public class MemberRecord {
    private int booksIssued;
    private final int bookLimit = 5; // Kullanıcıların 5 kitap limiti olmalı.
    private final LocalDate membershipDate = LocalDate.now();

    public int getBooksIssued() {
        return booksIssued;
    }

    public int getBookLimit() {
        return bookLimit;
    }

    public void incBooksIssued() {
        booksIssued++;
    }

    public void decBooksIssued() {
        booksIssued = Math.max(0, booksIssued - 1);
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }
}
