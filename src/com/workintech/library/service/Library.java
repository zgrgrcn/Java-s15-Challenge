package com.workintech.library.service;

import com.workintech.library.domain.Author;
import com.workintech.library.domain.Book;
import com.workintech.library.domain.BorrowRecord;
import com.workintech.library.domain.Category;
import com.workintech.library.domain.Invoice;
import com.workintech.library.domain.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Library {
    private final String name;
    private final Map<Integer, Book> books = new HashMap<>();
    private final Map<Integer, BorrowRecord> borrowRecords = new HashMap<>();
    private final Map<Integer, Invoice> invoicesById = new HashMap<>();
    private final Map<Integer, Invoice> invoicesByBookId = new HashMap<>();
    private final Map<Integer, Reader> readers = new HashMap<>();
    private final Set<String> authorNames = new HashSet<>();
    private int nextBookId = 1;
    private int nextInvoiceId = 1;

    public Library(String name) {
        this.name = name;
    }

    public void registerReader(Reader reader) {
        readers.put(reader.getId(), reader);
    }

    public Book addBook(String title, Author author, Category category) {
        Book book = new Book(nextBookId++, title, author, category);
        books.put(book.getId(), book);
        authorNames.add(author.getName());
        return book;
    }

    public List<Book> search(String term) {
        List<Book> result = new ArrayList<>();
        try {
            int id = Integer.parseInt(term);
            Book book = books.get(id);
            if (book != null) {
                result.add(book);
            }
        } catch (NumberFormatException ex) {
            String lowered = term.toLowerCase(Locale.ROOT);
            for (Book b : books.values()) {
                if (b.getTitle().toLowerCase(Locale.ROOT).contains(lowered) ||
                        b.getAuthor().getName().toLowerCase(Locale.ROOT).contains(lowered)) {
                    result.add(b);
                }
            }
        }
        return result;
    }

    public String updateBook(int id, String title, String authorName, Category category) {
        Book book = books.get(id);
        if (book == null) return "Kitap bulunamadı.";
        book.update(title, new Author(authorName), category);
        return "Güncellendi -> " + book;
    }

    public String deleteBook(int id) {
        Book book = books.get(id);
        if (book == null) return "Kitap bulunamadı.";
        if (!book.isAvailable()) return "Kitap ödünçte, silinemez.";
        books.remove(id);
        return "Silindi -> " + book;
    }

    public List<Book> listBooksByCategory(Category category) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getCategory() == category) result.add(b);
        }
        return result;
    }

    public List<Book> listBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getAuthor().getName().equalsIgnoreCase(author)) result.add(b);
        }
        return result;
    }

    public String borrowBook(int readerId, int bookId) {
        Reader reader = readers.get(readerId);
        if (reader == null) return "Kullanıcı bulunamadı.";
        Book book = books.get(bookId);
        if (book == null) return "Kitap bulunamadı.";
        if (!book.isAvailable()) return "Kitap zaten ödünç alınmış.";
        if (!reader.canBorrow()) return "Kullanıcı limitine ulaştı (5 kitap).";

        book.markBorrowed();
        reader.borrow(book);
        BorrowRecord br = new BorrowRecord(book, reader);
        borrowRecords.put(book.getId(), br);

        Invoice invoice = new Invoice(nextInvoiceId++, reader, book, 10.0);
        invoicesById.put(invoice.getId(), invoice);
        invoicesByBookId.put(book.getId(), invoice);

        return "Ödünç verildi -> " + book + " | Fatura: " + invoice;
    }

    public String returnBook(int readerId, int bookId) {
        Reader reader = readers.get(readerId);
        if (reader == null) return "Kullanıcı bulunamadı.";
        BorrowRecord record = borrowRecords.get(bookId);
        if (record == null || record.isReturned()) return "Aktif ödünç kaydı yok.";
        if (record.getReader().getId() != readerId) return "Bu kitabı alan kullanıcı farklı.";

        Book book = record.getBook();
        book.markReturned();
        reader.giveBack(book);
        record.markReturned();

        Invoice invoice = invoicesByBookId.get(bookId);
        if (invoice != null) {
            invoice.refund();
        }
        return "Kitap iade alındı -> " + book;
    }

    public List<Invoice> invoiceList() {
        return new ArrayList<>(invoicesById.values());
    }

    public Set<String> uniqueAuthors() {
        return new HashSet<>(authorNames);
    }
}
