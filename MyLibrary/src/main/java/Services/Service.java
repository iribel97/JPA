/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.util.Scanner;

/**
 *
 * @author irina
 */
public class Service extends Printable {

    Scanner scaner = new Scanner(System.in);

    public Service() {
    }

    public boolean menuServ() throws Exception {
        int opc = 0;
        AuthorService servA = new AuthorService();
        EditorialService servE = new EditorialService();
        BookService servB = new BookService();

        //Repetir el menu mientras la opc digitada sea mayor de 14
        do {
            try {
                menu();
                opc = scaner.nextInt();

                if (opc > 19) {
                    System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                }
            } catch (Exception e) {
                opc = 19;
                System.out.println("WRONGLY TYPED OPTION");
                scaner.nextLine();

            } 
        } while (opc > 18);

        switch (opc) {
            case 1:
                servA.insertAutor();
                break;
            case 2:
                servE.insertEditorial();
                break;
            case 3:
                servB.insertBook();
                break;
            case 4:
                servA.deletAuthor();
                break;
            case 5:
                servE.deleteEditorial();
                break;
            case 6:
                servB.deleteBook();
                break;
            case 7:
                servA.updateAuthor();
                break;
            case 8:
                servE.updateEditorial();
                break;
            case 9:
                servB.updateBook();
                break;
            case 10:
                servA.showAuthorByName();
                break;
            case 11:
                servB.showBookByISBN();
                break;
            case 12:
                servB.showBookByTitle();
                break;
            case 13:

                break;
            case 14:

                break;
            case 15:
                servB.showBooks();
                break;
            case 16:
                servE.showEditorials();
                break;
            case 17:
                servA.showAuthors();
                break;

        }

        return opc != 18;
    }

}
