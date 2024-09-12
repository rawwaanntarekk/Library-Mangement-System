package Models;

import Interfaces.IBookRepository;
import Interfaces.IMemberRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class LibraryServices {
    static IBookRepository bookRepository = Library.bookRepository;
    static IMemberRepository memberRepository = Library.memberRepository;
    static Scanner scanner = new Scanner(System.in);

    private static String inputString( String prompt) {
        System.out.print(prompt + ":");
        return scanner.nextLine();
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
    private static String inputISBN(){
        System.out.println("Enter the ISBN:");
        Scanner Scanner = new Scanner(System.in);
        return Scanner.nextLine();
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
        bookRepository.updateBook(inputISBN());
    }

    public static void updateMember() {
        memberRepository.updateMember(inputMember());
    }

    public static void getBooks() {
        for (Book book : bookRepository.GetBooks()) {
            System.out.println(book);
        }
    }

    private static void showOptions() {
        System.out.println("1. Add Book");
        System.out.println("2. Register Member");
        System.out.println("3. Update Book");
        System.out.println("4. Update Member");
        System.out.println("5. Get Books");
        System.out.println("0. Exit");
    }



    private static void  processOption(int option) {
        switch (option) {
            case 1 -> addBook();
            case 2 -> registerMember();
            case 3 -> updateBook();
            case 4 -> updateMember();
            case 5 -> getBooks();
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
