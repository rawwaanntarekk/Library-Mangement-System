package Interfaces;

import Models.Book;

import java.time.LocalDate;

public interface IBookRepository {



     void  addBook(String title, String author, String ISBN, LocalDate publicationDate);
     void updateBook(String ISBN);
     Book getBook(String ISBN);
     void deleteBook(String ISBN);

}
