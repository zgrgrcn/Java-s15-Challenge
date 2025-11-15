package com.workintech.library.domain;

import java.util.ArrayList;
import java.util.List;

public class Reader extends User {
    private final MemberRecord record = new MemberRecord();
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Reader(int id, String name) {
        super(id, name);
    }

    public boolean canBorrow() {
        return record.getBooksIssued() < record.getBookLimit();
    }

    public void borrow(Book book) {
        borrowedBooks.add(book);
        record.incBooksIssued();
    }

    public void giveBack(Book book) {
        borrowedBooks.remove(book);
        record.decBooksIssued();
    }

    public int currentBorrowCount() {
        return borrowedBooks.size();
    }

    @Override
    public String toString() {
        return "Reader{" + getId() + ", name='" + getName() + '\'' + ", borrowed=" + borrowedBooks.size() + '}';
    }
}
