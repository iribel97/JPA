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

    private final BookDAO dao;
    private final AuthorService servA = new AuthorService();
    private final EditorialService servE = new EditorialService();
    private final Scanner scan = new Scanner(System.in);

    public BookService() {
        this.dao = new BookDAO();
    }

    public void insertBook() throws Exception {

        List<Book> books = dao.selectBooks();

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

        //SI LA FUNCION BOOLEANA SE MANTUVO FALSE, SIGNIFICA QUE EL ISBN NO EXISTE
        if (!findBook(book)) {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  BOOK SUCCESSFULLY ADDED TO THE DATABASE        |");
            System.out.println("|-------------------------------------------------|");
            dao.insert(book);

        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|   BOOK ALREDY EXISTS                            |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //OPCION 6 DEL MENU
    public void deleteBook() throws Exception {

        printOpc6();
        showBooks();

        //PEDIR AL USUARIO
        System.out.print("   - SELECT BOOK ID: ");

        //INSTANCIAMOS OBJETO DE TIPO BOOK Y LE MANDAMOS EL ID QUE SELECCIONE EL USUARIO
        Book book = dao.selectBookByID(scan.nextLong());
        if (book != null) {
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

    //OPCION 9 DEL MENU
    public void updateBook() {
        int opc;
        printOpc9();
        try {
            System.out.println("|-------------------------------------------------|");
            System.out.print(" SELECT AN OPTION: ");
            opc = scan.nextInt();

            //MOSTRAR LIBROS
            showBooks();

            //PEDIR AL USUARIO
            System.out.println("|-------------------------------------------------|");
            System.out.print("   - SELECT BOOK ISBN: ");

            //INSTANCIAMOS OBJETO DE TIPO BOOK Y LE MANDAMOS EL ID QUE SELECCIONE EL USUARIO
            Book book = dao.selectBookByID(scan.nextLong());

            if (book != null) {
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
                        System.out.println("|-------------------------------------------------|");
                        System.out.print("   - COPIES: ");
                        book.setCopy(scan.nextInt());
                        break;
                    case 6:
                        System.out.println("|-------------------------------------------------|");
                        System.out.print("   - BORROWED COPIES: ");
                        book.setBorrowedCopies(scan.nextInt());
                        book.setRemaininCopies(book.getCopy() - book.getBorrowedCopies());
                        break;
                    case 7:
                        System.out.println("|-------------------------------------------------|");
                        System.out.print("   - REMAINING COPIES: ");
                        book.setRemaininCopies(scan.nextInt());
                        book.setBorrowedCopies(book.getCopy() - book.getBorrowedCopies());
                        break;
                    default:
                        System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                }
                dao.update(book);

            } else {
                System.out.println("|-------------------------------------------------|");
                System.out.println("| THE BOOK DOES NOT EXIST, PLEASE TRY AGAIN       |");
                System.out.println("|-------------------------------------------------|");
            }

        } catch (Exception e) {
            System.out.println("WRONGLY TYPED OPTION");
            scan.nextLine();
        }

    }

    //OPCION 11 DEL MENU
    public void showBookByISBN() throws Exception {
        printOpc11();
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
        printOpc12();
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
        printOpc13();
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
        printOpc13();
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

    //ACTUALIZAR EL ALTA DE LIBROS
    public void updateRegister() throws Exception {
        //DEBEMOS TRAER A TODOS LOS LIBROS DE LA TABLA
        List<Book> books = dao.selectBooks();

        //SE COMPRUEBA QUE LA LISTA NO SE ENCUENTRE VACIA
        if (books != null) {
            //SE RECORRE LA LISTA
            for (Book aux : books) {
                //PARA CAMBIAR EL ALTA FALSE PRIMERO SE COMPRUEBA QUE LOS LIBROS PRESTADOS SEAN IGUAL AL DE LAS COPIAS
                if (aux.getCopy() <= aux.getBorrowedCopies()) {
                    aux.setRegister(false);
                    dao.update(aux);
                    System.out.println("UPDATE REGISTER FOR " + aux.getTitle());
                }
                //SI TIENE PARAMETROS EN BLANCO TMB DEBERIA DE CAMBIAR EL ALTA A FALSE
                if (aux.getAuthor() == null || aux.getEditorial() == null || aux.getTitle().isBlank()
                        || aux.getYear() == 0) {
                    aux.setRegister(false);
                    dao.update(aux);
                    System.out.println("MISSING PARAMETERS IN " + aux.getTitle());
                } else if (aux.getAuthor() != null && aux.getEditorial() != null && !aux.getTitle().isBlank()
                        && aux.getYear() != 0 && aux.getCopy() > aux.getBorrowedCopies()) {
                    aux.setRegister(true);
                    dao.update(aux);
                }
            }
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

            imprimirCasilla(String.valueOf(aux.getCopy()), vCopies);
            imprimirCasilla(String.valueOf(aux.getBorrowedCopies()), vBC);
            imprimirCasilla(String.valueOf(aux.getRemaininCopies()), vRC);
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
        imprimirCasilla(String.valueOf(aux.getCopy()), vCopies);
        imprimirCasilla(String.valueOf(aux.getBorrowedCopies()), vBC);
        imprimirCasilla(String.valueOf(aux.getRemaininCopies()), vRC);
        System.out.println("|");
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
            imprimirCasilla(String.valueOf(aux.getCopy()), vCopies);
            imprimirCasilla(String.valueOf(aux.getBorrowedCopies()), vBC);
            imprimirCasilla(String.valueOf(aux.getRemaininCopies()), vRC);
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
}
