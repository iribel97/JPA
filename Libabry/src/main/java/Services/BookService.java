/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Book;
import Persistences.BookDAO;
import Persistences.LoanDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author irina
 */
public class BookService extends Printable {

    private final BookDAO dao;
    // SERVICIOS ---------------------------------------------------------------
    private final AuthorService servA = new AuthorService();
    private final EditorialService servE = new EditorialService();
    // -------------------------------------------------------------------------
    private final Scanner scan = new Scanner(System.in);

    public BookService() {
        this.dao = new BookDAO();
    }

    //OPCION 3 DEL MENU INSERTAR
    public void insertBook() throws Exception {
        int opcAut;
        List<Book> books = dao.selectBooks();

        long isbnB;
        String titleB;
        int yeraB, copiesB, borrowedCopiesB, remaininCopiesB;

        //INSTANCIAR NUESTRO OBJETO LIBRO
        Book book = new Book();

        //PEDIR DATOS AL USUARIO
        print1Opc3();
        System.out.print("   - ISBN: ");
        book.setIsbn(scan.nextLong());
        System.out.print("   - TITLE: ");
        scan.nextLine();
        titleB = scan.nextLine();
        book.setTitle(titleB);
        System.out.print("   - YEAR OF PUBLICATION: ");
        book.setYear(scan.nextInt());
        System.out.print("   - COPIES: ");
        book.setCopies(scan.nextInt());
        do {
            System.out.print("   - BORROWED COPIES: ");
            book.setBorrowedCopies(scan.nextInt());
            if (book.getBorrowedCopies() > book.getCopies()) {
                System.out.println("|-------------------------------------------------|");
                System.out.println("|TYPING ERROR, YOU HAVE ENTERED MORE BORROWED     |");
                System.out.println("|COPIES THAN THERE ARE AVAILABLE, PLEASE TRY AGAIN|");
                System.out.println("|-------------------------------------------------|");
            }
        } while (book.getBorrowedCopies() > book.getCopies());

        book.setRemaininCopies(book.getCopies() - book.getBorrowedCopies());

        servA.showAuthors();
        System.out.print("   - SELECT AN AUTHOR ID OR 0 TO EXIT: ");
        opcAut = scan.nextInt();
        if (opcAut != 0) {
            book.setAuthor(servA.selectOneAuthor(opcAut));
        }
        servE.showEditorials();
        System.out.print("   - SELECT AN EDITORIAL ID OR 0 TO EXIT: ");
        opcAut = scan.nextInt();
        if (opcAut != 0) {
            book.setEditorial(servE.selectOneEditorial(opcAut));
        }

        //SI LA FUNCION BOOLEANA SE MANTUVO FALSE, SIGNIFICA QUE EL ISBN NO EXISTE
        if (!findBook(book)) {
            dao.insert(book);
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  BOOK SUCCESSFULLY ADDED TO THE DATABASE        |");
            System.out.println("|-------------------------------------------------|");

        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|   BOOK ALREDY EXISTS                            |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //OPCION 3 DEL MENU ELIMINAR
    public void deleteBook() throws Exception {
        LoanDAO daoL = new LoanDAO();

        print2Opc3();
        showBooks();

        //PEDIR AL USUARIO
        System.out.print("   - SELECT BOOK ISBN OR 0 TO EXIT: ");
        long opc = scan.nextLong();

        if (opc != 0) {
            //INSTANCIAMOS OBJETO DE TIPO BOOK Y LE MANDAMOS EL ID QUE SELECCIONE EL USUARIO
            Book book = dao.selectBookByID(opc);
            if (book != null) {
                daoL.updateBookInNull(book.getIsbn());
                dao.delete(book.getIsbn());
                System.out.println("|-------------------------------------------------|");
                System.out.println("|  BOOK SUCCESSFULLY DELETED FROM THE DATABASE    |");
                System.out.println("|-------------------------------------------------|");
            } else {
                System.out.println("|-------------------------------------------------|");
                System.out.println("| THE BOOK DOES NOT EXIST, PLEASE TRY AGAIN       |");
                System.out.println("|-------------------------------------------------|");
            }
        }

    }

    //OPCION 3 DEL MENU ACTUALIZAR
    public void updateBook() {
        int opc;

        try {
            //MOSTRAR LIBROS
            showBooks();
            //PEDIR AL USUARIO
            System.out.println("|-------------------------------------------------|");
            System.out.print("   - SELECT BOOK ISBN OR 0 TO EXIT: ");
            long opcISBN = scan.nextLong();

            if (opcISBN != 0) {

                //INSTANCIAMOS OBJETO DE TIPO BOOK Y LE MANDAMOS EL ID QUE SELECCIONE EL USUARIO
                Book book = dao.selectBookByID(opcISBN);

                if (book != null) {
                    //MOSTRAR EL MENU CON LOS CAMPOS QUE EL USUARIO PUEDE MODIFICAR DEL LIBRO
                    print3Opc3();
                    
                    System.out.println("|-------------------------------------------------|");
                    System.out.print(" SELECT AN OPTION: ");
                    opc = scan.nextInt();
                    switch (opc) {
                        case 1:
                            System.out.println("|-------------------------------------------------|");
                            System.out.print("   - TITLE: ");
                            scan.nextLine();
                            book.setTitle(scan.nextLine());

                            break;
                        case 2:
                            System.out.println("|-------------------------------------------------|");
                            System.out.print("   - YEAR: ");
                            book.setYear(scan.nextInt());
                            break;
                        case 3:
                            System.out.println("|-------------------------------------------------|");
                            servA.showAuthors();
                            System.out.print("   - SELECT AN AUTHOR ID : ");
                            book.setAuthor(servA.selectOneAuthor(scan.nextInt()));
                            break;
                        case 4:
                            System.out.println("|-------------------------------------------------|");
                            servE.showEditorials();
                            System.out.print("   - SELECT AN EDITORIAL ID: ");
                            book.setEditorial(servE.selectOneEditorial(scan.nextInt()));
                            break;
                        case 5:
                            //CONDICION SI EL USUARIO INGRESA MENOS COPIAS DE LAS PRESTADAS
                            do {
                                System.out.println("|-------------------------------------------------|");
                                System.out.print("   - COPIES: ");
                                book.setCopies(scan.nextInt());

                                if (book.getCopies() < book.getBorrowedCopies()) {
                                    System.out.println("|-------------------------------------------------|");
                                    System.out.println("|ERROR, THERE CANNOT BE FEWER COPIES THAN THOSE   |");
                                    System.out.println("|BORROWED COPIES, TRY AGAIN                       |");
                                    System.out.println("|-------------------------------------------------|");
                                }
                            } while (book.getCopies() < book.getBorrowedCopies());

                            book.setRemaininCopies(book.getCopies() - book.getBorrowedCopies());

                            break;
                        case 6:
                            do {
                                System.out.println("|-------------------------------------------------|");
                                System.out.print("   - BORROWED COPIES: ");
                                book.setBorrowedCopies(scan.nextInt());

                                if (book.getCopies() < book.getBorrowedCopies()) {
                                    System.out.println("|-------------------------------------------------|");
                                    System.out.println("|ERROR, THERE CANNOT BE FEWER COPIES THAN THOSE   |");
                                    System.out.println("|BORROWED COPIES, TRY AGAIN                       |");
                                    System.out.println("|-------------------------------------------------|");
                                }

                            } while (book.getCopies() < book.getBorrowedCopies());

                            book.setRemaininCopies(book.getCopies() - book.getBorrowedCopies());
                            break;
                        case 7:
                            System.out.println("|-------------------------------------------------|");
                            System.out.print("   - REMAINING COPIES: ");
                            book.setRemaininCopies(scan.nextInt());
                            book.setCopies(book.getRemaininCopies() + book.getBorrowedCopies());
                            break;
                        default:
                            System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                    }

                    if (opc <= 7) {
                        dao.update(book);
                    }
                } else {
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("| THE BOOK DOES NOT EXIST, PLEASE TRY AGAIN       |");
                    System.out.println("|-------------------------------------------------|");
                }
            }

        } catch (Exception e) {
            System.out.println("WRONGLY TYPED OPTION");
            scan.nextLine();
        }

    }

    //OPCION 11 DEL MENU
    public void showBookByISBN() throws Exception {
//        printOpc11();
        System.out.print("  - BOOK'S ISBN: ");
        long isbnB = scan.nextLong();

        Book book = dao.selectBookByID(isbnB);

        if (book != null) {
            showABook(book);
        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("| THE BOOK DOES NOT EXIST, PLEASE TRY AGAIN       |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //OPCION 12 DEL MENU
    public void showBookByTitle() throws Exception {
//        printOpc12();
        System.out.print("   - BOOK'S TITLE: ");
        String titleB = scan.nextLine();

        List<Book> books = dao.selectBookByTitle(titleB);

        if (books != null) {
            showManyBooks(books);
        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("| THE BOOK DOES NOT EXIST, PLEASE TRY AGAIN       |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //OPCION 13 DEL MENU
    public void showBookByAuthor() throws Exception {
//        printOpc13();
        System.out.print("   - AUTHOR'S NAME: ");
        String authorB = scan.nextLine();

        List<Book> books = dao.selectBookByAuthor(authorB);

        if (books != null) {
            showManyBooks(books);
        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("| THE AUTHOR DOES NOT EXIST, PLEASE TRY AGAIN     |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //OPCION 14 DEL MENU
    public void showBookByEditorial() throws Exception {
//        printOpc13();
        System.out.print("   - EDITORIAL'S NAME: ");
        String editorialB = scan.nextLine();

        List<Book> books = dao.selectBookByEditorial(editorialB);

        if (books != null) {
            showManyBooks(books);
        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("| THE AUTHOR DOES NOT EXIST, PLEASE TRY AGAIN     |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //IMPRIMIR LIBROS ----------------------------------------------------------
    public void showBooks() throws Exception {
        //INSTANCIAMOS UNA LISTA DE OBJETOS DE TIPO LIBRO
        List<Book> books = dao.selectBooks();

        //VARIABLES PARA EL ENCABEZADO
        String vISBN = "____ ISBN ____", vTitle = "___________ TITLE ___________",
                vYear = "__ YEAR __", vAuthor = "_____ AUTHOR _____",
                vEditorial = "___ EDITORIAL ___", vCopies = "__ COPIES __",
                vBC = "___ B.C. ___", vRC = "___ R.C. ___";

        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                               BOOKS                                                               |");
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|" + vISBN + "|" + vTitle + "|" + vYear + "|" + vAuthor + "|" + vEditorial + "|" + vCopies + "|" + vBC + "|" + vRC + "|");
        if (books != null) {
            for (Book aux : books) {
                imprimirCasilla(String.valueOf(aux.getIsbn()), vISBN);
                imprimirCasilla(aux.getTitle(), vTitle);
                imprimirCasilla(String.valueOf(aux.getYear()), vYear);
                if (aux.getAuthor() == null) {
                    imprimirCasilla(" ", vAuthor);
                } else {
                    imprimirCasilla(aux.getAuthor().getName(), vAuthor);
                }
                if (aux.getEditorial() == null) {
                    imprimirCasilla(" ", vEditorial);
                } else {
                    imprimirCasilla(aux.getEditorial().getName(), vEditorial);
                }
                imprimirCasilla(String.valueOf(aux.getCopies()), vCopies);
                imprimirCasilla(String.valueOf(aux.getBorrowedCopies()), vBC);
                imprimirCasilla(String.valueOf(aux.getRemaininCopies()), vRC);
                System.out.println("|");
            }
        } else {
            imprimirCasilla(" ", vISBN);
            imprimirCasilla(" ", vTitle);
            imprimirCasilla(" ", vYear);
            imprimirCasilla(" ", vAuthor);
            imprimirCasilla(" ", vEditorial);
            imprimirCasilla(" ", vCopies);
            imprimirCasilla(" ", vBC);
            imprimirCasilla(" ", vRC);
            System.out.println("|");
        }

        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
    }

    //IMPRIMIR UN LIBRO --------------------------------------------------------
    private void showABook(Book aux) {
        //VARIABLES PARA EL ENCABEZADO
        String vISBN = "____ ISBN ____", vTitle = "___________ TITLE ___________",
                vYear = "__ YEAR __", vAuthor = "_____ AUTHOR _____",
                vEditorial = "___ EDITORIAL ___", vCopies = "__ COPIES __",
                vBC = "___ B.C. ___", vRC = "___ R.C. ___";

        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                               BOOK                                                                |");
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|" + vISBN + "|" + vTitle + "|" + vYear + "|" + vAuthor + "|" + vEditorial + "|" + vCopies + "|" + vBC + "|" + vRC + "|");

        if (aux != null) {
            imprimirCasilla(String.valueOf(aux.getIsbn()), vISBN);
            imprimirCasilla(aux.getTitle(), vTitle);
            imprimirCasilla(String.valueOf(aux.getYear()), vYear);
            if (aux.getAuthor() == null) {
                imprimirCasilla(" ", vAuthor);
            } else {
                imprimirCasilla(aux.getAuthor().getName(), vAuthor);
            }
            if (aux.getEditorial() == null) {
                imprimirCasilla(" ", vEditorial);
            } else {
                imprimirCasilla(aux.getEditorial().getName(), vEditorial);
            }
            imprimirCasilla(String.valueOf(aux.getCopies()), vCopies);

            imprimirCasilla(String.valueOf(aux.getBorrowedCopies()), vBC);
            imprimirCasilla(String.valueOf(aux.getRemaininCopies()), vRC);
            System.out.println("|");
        } else {
            imprimirCasilla(" ", vISBN);
            imprimirCasilla(" ", vTitle);
            imprimirCasilla(" ", vYear);
            imprimirCasilla(" ", vAuthor);
            imprimirCasilla(" ", vEditorial);
            imprimirCasilla(" ", vCopies);
            imprimirCasilla(" ", vBC);
            imprimirCasilla(" ", vRC);
            System.out.println("|");
        }
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
    }

    //IMPRIMIR LIBROS POR FILTRADO ---------------------------------------------
    private void showManyBooks(List<Book> books) {
        //VARIABLES PARA EL ENCABEZADO
        String vISBN = "____ ISBN ____", vTitle = "___________ TITLE ___________",
                vYear = "__ YEAR __", vAuthor = "_____ AUTHOR _____",
                vEditorial = "___ EDITORIAL ___", vCopies = "__ COPIES __",
                vBC = "___ B.C. ___", vRC = "___ R.C. ___";

        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                               BOOKS                                                               |");
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|" + vISBN + "|" + vTitle + "|" + vYear + "|" + vAuthor + "|" + vEditorial + "|" + vCopies + "|" + vBC + "|" + vRC + "|");
        if (books != null) {
            for (Book aux : books) {
                imprimirCasilla(String.valueOf(aux.getIsbn()), vISBN);
                imprimirCasilla(aux.getTitle(), vTitle);
                imprimirCasilla(String.valueOf(aux.getYear()), vYear);
                if (aux.getAuthor() == null) {
                    imprimirCasilla(" ", vAuthor);
                } else {
                    imprimirCasilla(aux.getAuthor().getName(), vAuthor);
                }
                if (aux.getEditorial() == null) {
                    imprimirCasilla(" ", vEditorial);
                } else {
                    imprimirCasilla(aux.getEditorial().getName(), vEditorial);
                }
                imprimirCasilla(String.valueOf(aux.getCopies()), vCopies);
                imprimirCasilla(String.valueOf(aux.getBorrowedCopies()), vBC);
                imprimirCasilla(String.valueOf(aux.getRemaininCopies()), vRC);
                System.out.println("|");
            }
        } else {
            imprimirCasilla(" ", vISBN);
            imprimirCasilla(" ", vTitle);
            imprimirCasilla(" ", vYear);
            imprimirCasilla(" ", vAuthor);
            imprimirCasilla(" ", vEditorial);
            imprimirCasilla(" ", vCopies);
            imprimirCasilla(" ", vBC);
            imprimirCasilla(" ", vRC);
            System.out.println("|");
        }
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------|");
    }

    //RETORNAR VALORES ---------------------------------------------------------
    /*VERIFICAR QUE EL LIBRO EXISTA*/
    private boolean findBook(Book book) throws Exception {
        //INSTANCIAMOS UNA LISTA DE OBJETOS DE TIPO LIBRO
        List<Book> books = dao.selectBooks();
        //COMPROBAMOS QUE LA TABLA LIBRO EN LA BASE DE DATOS NO SE ENCUENTRA VACIA
        if (!books.isEmpty()) {
            //BUCLE FOREACH PARA RECORRER LA LISTA
            for (Book aux : books) {
                //COMPROBAR SI EL ISBN INGRESADO POR EL USUARIO EXISTE
                if (aux.getIsbn() == book.getIsbn()) {
                    //CASO DE EXISTIR LA VARIABLE BOOLEANA SE VUELVE TRUE Y AVISA AL USUARIO QUE EL LIBRO YA EXISTE
                    return true;

                }
            }
        }

        return false;
    }

    /*UN LIBRO POR ISBN*/
    public Book selectABook(Long isbnB) throws Exception {
        return dao.selectBookByID(isbnB);
    }
}
