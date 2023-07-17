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

    AuthorService servA = new AuthorService();
    EditorialService servE = new EditorialService();
    BookService servB = new BookService();
    ClientService servC = new ClientService();

    Scanner scaner = new Scanner(System.in);

    public Service() {
    }

    public boolean menuServ() throws Exception {
        int opc = 0;

        //Repetir el menu mientras la opc digitada sea mayor de 14
        do {
            try {
                //mostrar el menu general
                menu();
                //lee la opc del usuario
                opc = scaner.nextInt();

                if (opc > 13) {
                    System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                }
            } catch (Exception e) {
                opc = 19;
                System.out.println("WRONGLY TYPED OPTION");
                scaner.nextLine();

            }
        } while (opc > 13);

        switch (opc) {
            case 1:
                case1();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
        }
        /*
        switch (opc) {
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
                servB.showBookByAuthor();
                break;
            case 14:
                servB.showBookByEditorial();
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
         */

        return opc != 13;
    }

    //INSERTAR DATOS EN LA BASE DE DATOS
    public void case1() {
        int opc;
        do {
            try {
                //MOSTRAR MENU DE OPCIONES PARA INSERTAR
                minieMenuOpc1();
                opc = scaner.nextInt();

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
                        servC.insertClient();
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                }
            } catch (Exception e) {
                opc = 19;
                System.out.println("WRONGLY TYPED OPTION");
                scaner.nextLine();
            }
        } while (opc != 6);

    }

}
