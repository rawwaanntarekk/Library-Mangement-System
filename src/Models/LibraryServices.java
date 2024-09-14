package Models;

import Interfaces.IBookRepository;
import Interfaces.IMemberRepository;
import Services.BorrowService;
import java.time.LocalDate;
import java.util.Scanner;

public class LibraryServices {
    static IBookRepository bookRepository = Library.bookRepository;
    static IMemberRepository memberRepository = Library.memberRepository;
    static Scanner scanner = new Scanner(System.in);

    private static String inputString(String prompt) {
        System.out.print(prompt + ":");
        String input = scanner.nextLine();
        while (!validateInput(prompt, input)) {
            System.out.print("Invalid input, please enter a valid " + prompt + ":");
            input = scanner.nextLine();
        }
        return input;
    }

    private static boolean validateInput(String prompt, String input) {
        return switch (prompt) {
            case "Title", "Author", "Name" -> input.matches("^[a-zA-Z\\s'-]+$");
            case "ISBN" -> input.matches("^[0-9]{13}$");
            case "Phone" -> input.matches("^01[0125][0-9]{8}$");
            case "Email" -> input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            default -> false;
        };
    }


    private static LocalDate inputDate() {
        System.out.print("Publication Date" + " (YYYY-MM-DD):");
        return LocalDate.parse(scanner.nextLine());
    }

    private static Book inputBook(){
        System.out.println("Enter the book details:");
        String title = inputString("Title");
        String author = inputString("Author");
        String ISBN = inputString("ISBN");
        LocalDate publicationDate = inputDate();
        return new Book(title, author, ISBN, publicationDate);
    }



    private static Member inputMember(){
        System.out.println("Enter the member details:");
        String name = inputString("Name");
        String phone = inputString( "Phone");
        String email = inputString( "Email");
        return new Member(name, phone, email);
    }
    private static Integer inputChoice(){
        try {
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }


    public static void addBook() {
        bookRepository.addBook(inputBook());
        System.out.println("Book added successfully");
    }

    public static void registerMember() {
        memberRepository.addMember(inputMember());
        System.out.println("Member registered successfully");

    }

    public static void updateBook() {
        bookRepository.updateBook(inputString("ISBN"));
        Book book = bookRepository.findBookByIsbn(isbn);
  if (book != null) {
            System.out.println("Current details of the book: " + book);
            System.out.println("Enter new details for the book:");
            String newTitle = inputString("Title");
            String newAuthor = inputString("Author");
            LocalDate newPublicationDate = inputDate();

            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPublicationDate(newPublicationDate);

            bookRepository.updateBook(book);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found with ISBN: " + isbn);
        }
    }
    public static void updateMember() {
        memberRepository.updateMember(inputMember());
        String email = inputString("Email");
        Member member = memberRepository.findMemberByEmail(email);

        if (member != null) {
            System.out.println("Current details of the member: " + member);
            System.out.println("Enter new details for the member:");
            String newName = inputString("Name");
            String newPhone = inputString("Phone");

            member.setName(newName);
            member.setPhone(newPhone);

            memberRepository.updateMember(member);
            System.out.println("Member updated successfully.");
        } else {
            System.out.println("Member not found with email: " + email);
        }
    }

  public static void getBooks() {
        for (Book book : bookRepository.getBooks()) {
            System.out.println(book);
        }
    }

    // Borrow a Book
    public static void borrowBook() {
        String isbn = inputString("ISBN");
        String memberId = inputString("Member ID");
        LocalDate dueDate = inputDate();  // Prompt the user for the due date

        BorrowService borrowService = new BorrowService(bookRepository, memberRepository);
        borrowService.borrowBook(isbn, memberId, dueDate);  // Call borrowBook method from BorrowService
    }

    // Return a Book
    public static void returnBook() {
        String isbn = inputString("ISBN");
        String memberId = inputString("Member ID");
        LocalDate returnDate = LocalDate.now();  // Assume the return date is the current date

        BorrowService borrowService = new BorrowService(bookRepository, memberRepository);
        borrowService.returnBook(isbn, memberId, returnDate);  // Call returnBook method from BorrowService
    }
  private static void showOptions() {
        System.out.println("1. Add Book");
        System.out.println("2. Register Member");
        System.out.println("3. Update Book");
        System.out.println("4. Update Member");
        System.out.println("5. Get Books");
        System.out.println("6. Borrow Book");
        System.out.println("7. Return Book");
        System.out.println("0. Exit");
    }

    // Process user input
    private static void processOption(int option) {
        switch (option) {
            case 1 -> addBook();
            case 2 -> registerMember();
            case 3 -> updateBook();
            case 4 -> updateMember();
            case 5 -> getBooks();
            case 6 -> borrowBook();  // Borrow book option
            case 7 -> returnBook();  // Return book option
            case 0 -> {
                System.out.println("Exiting...");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice, please choose again.");
        }
    }

    public static void run() {
        int option;
        do {
            showOptions();
            option = inputChoice();
            processOption(option);
        } while (option != 0);
    }

}
