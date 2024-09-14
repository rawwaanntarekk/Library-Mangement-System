package Models;

import Models.Book;
import Models.BookLoan;
import Models.BookStatus;
import Models.Member;
import Interfaces.IBookRepository;
import Interfaces.IMemberRepository;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class BorrowService {
    private IBookRepository bookRepository;
    private IMemberRepository memberRepository;
    private Map<String, BookLoan> bookLoans;

    // Constructor for BorrowService
    public BorrowService(IBookRepository bookRepository, IMemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.bookLoans = new HashMap<>();
    }

    // Borrow a book
    public void borrowBook(String isbn, String memberId, LocalDate dueDate) {
        Book book = bookRepository.findBookByIsbn(isbn);
        Member member = memberRepository.findMemberById(memberId);

        if (book != null && member != null) {
            if (book.getStatus() == BookStatus.AVAILABLE) {
                // Update book status and create a book loan
                updateBookStatus(book, BookStatus.CHECKED_OUT);
                BookLoan loan = new BookLoan(memberId, isbn, dueDate);
                bookLoans.put(isbn, loan);
                System.out.println("Book borrowed: " + book.getTitle() + " by " + member.getName());
            } else {
                System.out.println("Book is not available.");
            }
        } else {
            System.out.println("Invalid book ISBN or member ID.");
        }
    }

    // Return a book and calculate overdue fines if applicable
    public void returnBook(String isbn, String memberId, LocalDate returnDate) {
        Book book = bookRepository.findBookByIsbn(isbn);
        Member member = memberRepository.findMemberById(memberId);
        BookLoan loan = bookLoans.get(isbn);

        if (book != null && member != null && loan != null && loan.getMemberId().equals(memberId)) {
            loan.calculateOverdueFines(returnDate);
            updateBookStatus(book, BookStatus.AVAILABLE);
            bookLoans.remove(isbn);
            System.out.println("Book returned: " + book.getTitle());
            if (loan.getOverdueFines() > 0) {
                System.out.println("Overdue fines: $" + loan.getOverdueFines());
            }
        } else {
            System.out.println("Book or member not found, or invalid return.");
        }
    }

    // Update book status
    public void updateBookStatus(Book book, BookStatus newStatus) {
        book.setStatus(newStatus);
        bookRepository.updateBook(book);  // Assuming updateBook method exists in the repository
        System.out.println("Book status updated to " + newStatus);
    }
}
