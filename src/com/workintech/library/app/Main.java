package com.workintech.library.app;

import com.workintech.library.domain.Author;
import com.workintech.library.domain.Category;
import com.workintech.library.domain.Librarian;
import com.workintech.library.domain.Reader;
import com.workintech.library.service.Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library("Workintech Library");
        Librarian librarian = new Librarian(1, "Admin", library);

        Reader ali = new Reader(1, "Ali");
        Reader ayse = new Reader(2, "Ayse");
        library.registerReader(ali);
        library.registerReader(ayse);
        librarian.addSampleBooks();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": // Sisteme yeni kitap eklenebilir.
                    System.out.print("Başlık: ");
                    String title = scanner.nextLine();
                    System.out.print("Yazar adı: ");
                    String authorName = scanner.nextLine();
                    System.out.print("Kategori (FICTION, NON_FICTION, HISTORY, SCIENCE, TECHNOLOGY, MAGAZINE): ");
                    Category category = Category.safeValueOf(scanner.nextLine());
                    System.out.println("Eklendi -> " + librarian.addBook(title, new Author(authorName), category));
                    break;
                case "2": // Sistemden id, isim veya yazar bilgisine göre bir kitap seçilebilir
                    System.out.print("Arama terimi (id veya metin): ");
                    librarian.searchBooks(scanner.nextLine()).forEach(System.out::println);
                    break;
                case "3": // Sistemde var olan bir kitabın bilgileri güncellenebilir.
                    System.out.print("Güncellenecek kitap id: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Yeni başlık: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Yeni yazar adı: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Yeni kategori: ");
                    Category newCategory = Category.safeValueOf(scanner.nextLine());
                    System.out.println(librarian.updateBook(updateId, newTitle, newAuthor, newCategory));
                    break;
                case "4": // Sistemde var olan bir kitap silinebilir.
                    System.out.print("Silinecek kitap id: ");
                    System.out.println(librarian.deleteBook(Integer.parseInt(scanner.nextLine())));
                    break;
                case "5": // Sistemde var olan bir kategorideki tüm kitaplar listelenebilir.
                    System.out.print("Kategori: ");
                    librarian.listByCategory(Category.safeValueOf(scanner.nextLine())).forEach(System.out::println);
                    break;
                case "6": // Sistemde var olan bir yazarın tüm kitapları listelenebilir
                    System.out.print("Yazar: ");
                    librarian.listByAuthor(scanner.nextLine()).forEach(System.out::println);
                    break;
                case "7": // Bir kullanıcı sistemde kitap mevcutsa ödünç alabilir.
                    System.out.print("Kullanıcı id: ");
                    int readerId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Kitap id: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.println(librarian.borrowBook(readerId, bookId));
                    break;
                case "8": // Kullanıcı kitap geri teslim edebilir.
                    System.out.print("Kullanıcı id: ");
                    int retReaderId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Kitap id: ");
                    int retBookId = Integer.parseInt(scanner.nextLine());
                    System.out.println(librarian.returnBook(retReaderId, retBookId));
                    break;
                case "9": // Sistem faturaları listeleyebilir.
                    librarian.showInvoices().forEach(System.out::println);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        }
        scanner.close();
        System.out.println("Güle güle!");
    }

    private static void printMenu() {
        System.out.println("\n---- Kütüphane Konsolu ----");
        System.out.println("1) Kitap ekle");
        System.out.println("2) Kitap ara (id/isim/yazar)");
        System.out.println("3) Kitap güncelle");
        System.out.println("4) Kitap sil");
        System.out.println("5) Kategoriye göre listele");
        System.out.println("6) Yazara göre listele");
        System.out.println("7) Kitap ödünç al");
        System.out.println("8) Kitap iade et");
        System.out.println("9) Faturaları listele");
        System.out.println("0) Çıkış");
        System.out.print("Seçim: ");
    }
}
