package Models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookLoan {
    private String memberId;
    private String bookId;
    private LocalDate dueDate;
    private double overdueFines;

    // Constructor for BookLoan
    public BookLoan(String memberId, String bookId, LocalDate dueDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.dueDate = dueDate;
        this.overdueFines = 0.0;
    }

    // Getters and setters
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getOverdueFines() {
        return overdueFines;
    }

    public void setOverdueFines(double overdueFines) {
        this.overdueFines = overdueFines;
    }

    // Calculate overdue fines based on the return date
    public void calculateOverdueFines(LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
            // Assuming a fine rate of $0.50 per day
            this.overdueFines = daysOverdue * 0.50;
        } else {
            this.overdueFines = 0.0;
        }
    }
}
