package Models;

import Interfaces.IBookRepository;
import Interfaces.IMemberRepository;
import Repositories.BookRepository;
import Repositories.MemberRepository;



public class Library {
    static IBookRepository bookRepository = new BookRepository();
    static IMemberRepository memberRepository = new MemberRepository();


    public static void run(){
        LibraryServices.run();
    }
}
