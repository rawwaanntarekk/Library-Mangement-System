package Repositories;

import Interfaces.IBookRepository;
import Models.Book;

import java.time.LocalDate;
import java.util.SequencedCollection;

import static Models.BookStatus.available;
import static Models.BookStatus.loaned;

public class BookRepository implements IBookRepository {
    SequencedCollection<Book> books;
    @Override
    public void addBook(String title, String author, String ISBN, LocalDate publicationDate) {
       Book book = new Book(title, author, ISBN, publicationDate);
       book.setStatus(available);
       books.add(book);
    }

    @Override
    public void updateBook(String ISBN) {
        Book book = getBook(ISBN);
        if (book != null) {
            book.setStatus(book.getStatus() == available ? loaned : available);
        }
    }

    @Override
    public Book getBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }




    @Override
    public void deleteBook(String ISBN) {
        Book book = getBook(ISBN);
        if (book != null) {
            books.remove(book);
        }
    }
}
