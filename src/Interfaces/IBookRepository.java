package Interfaces;

import Models.Book;
import java.util.ArrayList;

public interface IBookRepository {



     void  addBook(Book book);
     void updateBook(String ISBN);
     Book getBook(String ISBN);
     ArrayList<Book> getBooks();

}
