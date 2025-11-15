package com.workintech.library.domain;

import com.workintech.library.service.Library;

import java.util.List;

public class Librarian extends User {
    private final Library library;

    public Librarian(int id, String name, Library library) {
        super(id, name);
        this.library = library;
    }

    public Book addBook(String title, Author author, Category category) {
        return library.addBook(title, author, category);
    }

    public String updateBook(int id, String title, String authorName, Category category) {
        return library.updateBook(id, title, authorName, category);
    }

    public String deleteBook(int id) {
        return library.deleteBook(id);
    }

    public List<Book> listByCategory(Category category) {
        return library.listBooksByCategory(category);
    }

    public List<Book> listByAuthor(String author) {
        return library.listBooksByAuthor(author);
    }

    public List<Book> searchBooks(String term) {
        return library.search(term);
    }

    public String borrowBook(int readerId, int bookId) {
        return library.borrowBook(readerId, bookId);
    }

    public String returnBook(int readerId, int bookId) {
        return library.returnBook(readerId, bookId);
    }

    public List<Invoice> showInvoices() {
        return library.invoiceList();
    }

    public void addSampleBooks() {
        addBook("Clean Code", new Author("Robert C. Martin"), Category.TECHNOLOGY);
        addBook("The Pragmatic Programmer", new Author("Andrew Hunt"), Category.TECHNOLOGY);
        addBook("Lord of the Rings", new Author("J.R.R. Tolkien"), Category.FICTION);
        addBook("A Brief History of Time", new Author("Stephen Hawking"), Category.SCIENCE);
    }
}
