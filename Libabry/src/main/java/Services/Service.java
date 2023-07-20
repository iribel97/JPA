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
    //AUTOR --------------------------------------------------------------------
    AuthorService servA = new AuthorService();
    //EDITORIAL ----------------------------------------------------------------
    EditorialService servE = new EditorialService();
    //LIBRO --------------------------------------------------------------------
    BookService servB = new BookService();
    //CLIENTE ------------------------------------------------------------------
    ClientService servC = new ClientService();
    //PRESTAMO -----------------------------------------------------------------
    LoanService servL = new LoanService();
    //--------------------------------------------------------------------------
    
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

                if (opc > 10) {
                    System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                }
            } catch (Exception e) {
                opc = 19;
                System.out.println("WRONGLY TYPED OPTION");
                scaner.nextLine();

            }
        } while (opc > 10);

        switch (opc) {
            case 1:
                case1();
                break;
            case 2:
                case2();
                break;
            case 3:
                case3();
                break;
            case 4:
                case4();
                break;
            case 5:
                servB.showBooks();
                break;
            case 6:
                servE.showEditorials();
                break;
            case 7:
                servA.showAuthors();
                break;
            case 8:
                servC.printClients();
                break;
            case 9:
                servL.showLoans();
                break;
        }
        /*
        switch (opc) {
            case 14:
                
                break;
            case 15:
                
                break;
            case 16:
                
                break;
            case 17:
                
                break;

        }
         */

        return opc != 10;
    }

    //INSERTAR DATOS EN LA BASE DE DATOS --------------------------------------------------------------------------
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
                        servL.insertLoan();
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

    //ELIMINAR DATOS EN LA BASE DE DATOS --------------------------------------------------------------------------
    public void case2() {
        int opc;
        do {
            try {
                //MOSTRAR MENU DE OPCIONES PARA ELIMINAR
                minieMenuOpc2();
                opc = scaner.nextInt();

                switch (opc) {
                    case 1:
                        servA.deletAuthor();
                        break;
                    case 2:
                        servE.deleteEditorial();
                        break;
                    case 3:
                        servB.deleteBook();
                        break;
                    case 4:
                        servC.deleteClient();
                        break;
                    case 5:
                        servL.deleteLoan();
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
    
    //ACTUALIZAR DATOS EN LA BASE DE DATOS --------------------------------------------------------------------------
    public void case3(){
        int opc;
        do {
            try {
                //MOSTRAR MENU DE OPCIONES PARA ELIMINAR
                minieMenuOpc3();
                opc = scaner.nextInt();

                switch (opc) {
                    case 1:
                        servA.updateAuthor();
                        break;
                    case 2:
                        servE.updateEditorial();
                        break;
                    case 3:
                        servB.updateBook();
                        break;
                    case 4:
                        servC.updateClient();
                        break;
                    case 5:
                        servL.updateLoan();
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
    
    //BUSCAR DATOS EN LA BASE DE DATOS --------------------------------------------------------------------------
     public void case4(){
        int opc;
        do {
            try {
                //MOSTRAR MENU DE OPCIONES PARA ELIMINAR
                minieMenuOpc4();
                opc = scaner.nextInt();

                switch (opc) {
                    case 1:
                        servA.showAuthorByName();
                        break;
                    case 2:
                        servB.showBookByISBN();
                        break;
                    case 3:
                        servB.showBookByTitle();
                        break;
                    case 4:
                        servB.showBookByAuthor();
                        break;
                    case 5:
                        servB.showBookByEditorial();
                        break;
                    case 6:
                        servL.searchLoanByClient();
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                }
            } catch (Exception e) {
                opc = 19;
                System.out.println("WRONGLY TYPED OPTION");
                scaner.nextLine();
            }
        } while (opc != 7);
    }
}
