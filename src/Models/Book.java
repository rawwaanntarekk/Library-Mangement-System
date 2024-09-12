package Models;

import java.time.LocalDate;

public class Book {
    String title;
    String author;
    String ISBN;
    LocalDate publicationDate;
    BookStatus status;

    public Book(String title, String author, String ISBN, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationDate = publicationDate;
        this.status = BookStatus.available;
    }

    public String getISBN() {
        return ISBN;
    }


    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book Details: " +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", publicationDate=" + publicationDate +
                ", status=" + status ;
    }
}
