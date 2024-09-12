package Repositories;

import Interfaces.IBookRepository;
import Models.Book;

import java.util.ArrayList;


import static Models.BookStatus.available;
import static Models.BookStatus.loaned;

public class BookRepository implements IBookRepository {
    ArrayList<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
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
    public ArrayList<Book> getBooks() {
        return books;
    }



}
