/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Book;
import Persistences.BookDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author irina
 */
public class BookService extends Printable {

    BookDAO dao;
    AuthorService servA = new AuthorService();
    EditorialService servE = new EditorialService();
    Scanner scan = new Scanner(System.in);

    public BookService() {
        this.dao = new BookDAO();
    }

    public void insertBook() throws Exception {
        boolean flag = false;

        List<Book> books = dao.selectEditorial();

        long isbnB;
        String titleB;
        int yeraB, copiesB, borrowedCopiesB, remaininCopiesB;

        //INSTANCIAR NUESTRO OBJETO LIBRO
        Book book = new Book();

        //PEDIR DATOS AL USUARIO
        printOpc3();
        System.out.print("   - ISBN: ");
        book.setIsbn(scan.nextLong());
        System.out.print("   - TITLE: ");
        scan.nextLine();
        titleB = scan.nextLine();
        book.setTitle(titleB);
        System.out.print("   - YEAR OF PUBLICATION: ");
        book.setYear(scan.nextInt());
        System.out.print("   - COPIES: ");
        book.setCopy(scan.nextInt());
        System.out.print("   - BORROWED COPIES: ");
        book.setBorrowedCopies(scan.nextInt());
        System.out.print("   - REMAININ COPIES: ");
        book.setRemaininCopies(scan.nextInt());

        servA.showAuthors();
        System.out.print("   - SELECT AN AUTHOR ID: ");
        book.setAuthor(servA.selectOneAuthor(scan.nextInt()));

        servE.showEditorials();
        System.out.print("   - SELECT AN EDITORIAL ID: ");
        book.setEditorial(servE.selectOneEditorial(scan.nextInt()));

        //COMPROBAMOS QUE LA TABLA LIBRO EN LA BASE DE DATOS NO SE ENCUENTRA VACIA
        if (!books.isEmpty()) {
            //BUCLE FOREACH PARA RECORRER LA LISTA
            for (Book aux : books) {
                //COMPROBAR SI EL ISBN INGRESADO POR EL USUARIO EXISTE
                if (aux.getIsbn() == book.getIsbn()) {
                    //CASO DE EXISTIR LA VARIABLE BOOLEANA SE VUELVE TRUE Y AVISA AL USUARIO QUE EL LIBRO YA EXISTE
                    flag = true;
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("|   BOOK ALREDY EXISTS                            |");
                    System.out.println("|-------------------------------------------------|");
                }
            }
        }

        //SI LA VARIABLE BOOLEANA SE MANTUVO FALSE, SIGNIFICA QUE EL ISBN NO EXISTE
        if (!flag) {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  BOOK SUCCESSFULLY ADDED TO THE DATABASE        |");
            System.out.println("|-------------------------------------------------|");
            dao.insert(book);

        }
    }
}
