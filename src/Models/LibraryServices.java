package Models;

import Interfaces.IBookRepository;
import Interfaces.IMemberRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class LibraryServices {
    static IBookRepository bookRepository = Library.bookRepository;
    static IMemberRepository memberRepository = Library.memberRepository;

    private static String inputString(Scanner scanner, String prompt) {
        System.out.print(prompt + ":");
        return scanner.nextLine();
    }

    private static LocalDate inputDate(Scanner scanner) {
        System.out.print("Publication Date" + " (YYYY-MM-DD):");
        return LocalDate.parse(scanner.nextLine());
    }



    private static Book inputBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the book details:");
        String title = inputString(scanner, "Title");
        String author = inputString(scanner, "Author");
        String ISBN = inputString(scanner, "ISBN");
        LocalDate publicationDate = inputDate(scanner);
        return new Book(title, author, ISBN, publicationDate);
    }



    private static Member inputMember(){
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Enter the member details:");
        String name = inputString(Scanner, "Name");
        String phone = inputString(Scanner, "Phone");
        String email = inputString(Scanner, "Email");
        return new Member(name, phone, email);
    }
    private static String inputISBN(){
        System.out.println("Enter the ISBN:");
        Scanner Scanner = new Scanner(System.in);
        return Scanner.nextLine();
    }
    private static Integer inputChoice(){
        System.out.print("Enter your choice:");
        Scanner Scanner = new Scanner(System.in);
        return Scanner.nextInt();
    }


    public static void addBook() {
        bookRepository.addBook(inputBook());
        System.out.println("Book added successfully");
    }

    public static void registerMember() {
        Scanner scanner = new Scanner(System.in);
        memberRepository.addMember(inputMember());
        System.out.println("Member registered successfully");
        Member member = memberRepository.getMember(inputString(scanner, "Enter the member name to search"));
        System.out.println(member);

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

    private static int checkOption(int option) {
        if (option < 1 || option > 5) {
            return 0;
        }
        return option;
    }

    private static void  processOption(int option) {
        switch (option) {
            case 1 -> addBook();
            case 2 -> registerMember();
            case 3 -> updateBook();
            case 4 -> updateMember();
            case 5 -> getBooks();

        }
    }

    public static void run() {
        int option;
        do {
            showOptions();
            option = inputChoice();
            checkOption(option);
            processOption(option);
        } while (option != 0);
    }

}
