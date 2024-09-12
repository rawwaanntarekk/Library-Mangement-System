package Models;

import Interfaces.IBookRepository;

import java.time.LocalDate;

public class Library {
    static IBookRepository bookRepository;

    public static void addBook(String title, String author, String ISBN, LocalDate publicationDate) {
        bookRepository.addBook(title, author, ISBN, publicationDate);
    }
}
