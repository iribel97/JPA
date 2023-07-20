/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Loan;
import Persistences.LoanDAO;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author irina
 */
public class LoanService extends Printable {

    private final LoanDAO dao;
    // SERVICIOS ---------------------------------------------------------------
    BookService servB = new BookService();
    ClientService servC = new ClientService();
    // -------------------------------------------------------------------------
    private final Scanner scaner = new Scanner(System.in);

    public LoanService() {
        this.dao = new LoanDAO();
    }

    //OPCION 5 DEL MENU INSERT
    public void insertLoan() throws Exception {
        LocalDate fechaP;
        //INSTANCIAR OBJETO DE TIPO PRESTAMO
        Loan loan = new Loan();

        print1Opc5();

        //Mostrar los libros para que el usuario seleccione
        servB.showBooks();
        //Pedir al usuario que escoja el ISBN del libro o seleccione 0 para no insertar ni uno
        System.out.print("   - SELECT A BOOK ISBN OR 0 TO EXIT: ");
        long isbn = scaner.nextLong();
        /*si la seleccion del usuario no es 0, se procede a setear el atributo LIBRO por el que el 
        usuario selecciono*/
        if (isbn != 0) {
            loan.setBook(servB.selectABook(isbn));

            //UNA VEZ QUE SELECCIONA EL LIBRO, PREGUNTAMOS CUANDO SE HIZO EL PRESTAMO
            /*
                SE DEBE DE COMPROFAR QUE EL AÑO QUE SE INGRESE POR CONSOLA NO SEA INFERIOR AL AÑO DE PUBLICACION
                EN CASO DE QUE SI SEA, SE DEBE DE VOLVER A PEDIR AL USUARIO
             */
            do {
                System.out.print("   - Loan Date (yyyy-mm-dd): ");
                fechaP = LocalDate.parse(scaner.next());
                if (fechaP.getYear() < loan.getBook().getYear()) {

                    System.out.println("|-------------------------------------------------|");
                    System.out.println("| THE ENTERED YEAR IS EARLIER THAN BOOK'S         |");
                    System.out.println("| PUBLICATION YEAR. PLEASE TRY AGAIN              |");
                    System.out.println("|-------------------------------------------------|");
                }
            } while (fechaP.getYear() < loan.getBook().getYear());
            //UNA VEZ QUE SE VALIDA QUE EL AÑO INGRESADO SEA MAYOR O IGUAL QUE EL AÑO DE PUBLICACION, 
            //SE PROCEDE A GUARDAR EN EL OBJETO PRESTAMO

            loan.setLoanDate(java.sql.Date.valueOf(fechaP));

            //PREGUNTAMOS SI SE DESEA INGRESAR LA FECHA DE RETORNO
            System.out.print("   - DO YOU WANT TO INSERT THE RETURN DATE? (y/n): ");
            String opcS = scaner.next().toLowerCase();
            if (opcS.charAt(0) == 'y') {
                //SE DEBE DE COMPROBAR QUE LA FECHA INGRESADA SEA MAYOR QUE LA FECHA DEL PRESTAMO
                do {
                    System.out.print("   - Return Date (yyyy-mm-dd): ");
                    fechaP = LocalDate.parse(scaner.next());

                    if (loan.getLoanDate().after(loan.getReturnDate())) {
                        System.out.println("|-------------------------------------------------|");
                        System.out.println("| WRONG, TRY AGAIN                                |");
                        System.out.println("|-------------------------------------------------|");
                    }
                } while (loan.getLoanDate().after(loan.getReturnDate()));

                loan.setReturnDate(java.sql.Date.valueOf(fechaP));
            }

            //Mostrar clientes para que el usuario seleccione
            servC.printClients();
            System.out.print("   - SELECT A CLIENT ID OR 0 TO EXIT: ");
            int idC = scaner.nextInt();

            if (idC != 0) {
                loan.setClient(servC.slectClientID(idC));
            }
            //Insertar el objeto prestamo en la base de datos
            dao.insert(loan);

            // Indicarle al usuario que se agrego correctamente
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  LOAN SUCCESSFULLY ADDED TO THE DATABASE        |");
            System.out.println("|-------------------------------------------------|");
        }

    }

    //OPCION 5 DEL MENU ELIMINAR
    public void deleteLoan() throws Exception {

        print2Opc5();

        showLoans();

        //PEDIR AL USUARIO
        System.out.print("   - SELECT LOAN ID OR 0 TO EXIT: ");
        int opc = scaner.nextInt();

        if (opc != 0) {
            Loan loan = dao.selectLoanByID(opc);

            if (loan != null) {
                dao.delete(loan.getId());
                System.out.println("|-------------------------------------------------|");
                System.out.println("|  LOAN SUCCESSFULLY DELETED FROM THE DATABASE    |");
                System.out.println("|-------------------------------------------------|");
            } else {
                System.out.println("|-------------------------------------------------|");
                System.out.println("| THE LOAN DOES NOT EXIST, PLEASE TRY AGAIN       |");
                System.out.println("|-------------------------------------------------|");
            }
        }

    }

    //OPCION 5 DEL MENU ACTUALIZAR
    public void updateLoan() {
        boolean bandera;
        int opcM;
        LocalDate fechaP;
        try {

            //MOSTRAR TODOS LOS PRESTAMOS PARA SELECCIONAR CUAL DESEA EDITAR
            showLoans();

            //PEDIR AL USUARIO
            System.out.print("   - SELECT LOAN ID OR 0 TO EXIT: ");
            int opc = scaner.nextInt();

            if (opc != 0) {
                //SE INSTANCIA UNOBJETO DE TIPO PRESTAMO QUE GUARDE EL ID QUE SE LEYOPOR CONSOLA
                Loan loan = dao.selectLoanByID(opc);

                //COMPROBAMOS QUE NO SEA NULO PARA CONTINUAR
                if (loan != null) {
                    //MOSTRAR EL MENU CON LOS CAMPOS QUE EL USUARIO PUEDE MODIFICAR DEL PRESTAMO
                    print3Opc5();

                    System.out.println("|-------------------------------------------------|");
                    System.out.print(" SELECT AN OPTION: ");
                    opcM = scaner.nextInt();

                    System.out.println("|-------------------------------------------------|");

                    switch (opcM) {
                        case 1:

                            //PEDIR LA FECHA DEL PRESTAMO MIENTRAS QUE:
                            /*
                              - EL AÑO DE LA FECHA DEL PRESTAMO SEA ANTES DEL AÑO DE PUBLICACION DEL LIBRO
                            O
                              - LA FECHA DEL PRESTAMO SEA DESPUES DE LA FECHA DE RETORNO 
                             */
                            do {
                                bandera = false;
                                System.out.print("   - Loan Date (yyyy-mm-dd): ");
                                fechaP = LocalDate.parse(scaner.next());

                                loan.setLoanDate(java.sql.Date.valueOf(fechaP));
                                if (fechaP.getYear() < loan.getBook().getYear()) {

                                    System.out.println("|-------------------------------------------------|");
                                    System.out.println("| THE ENTERED YEAR IS EARLIER THAN BOOK'S         |");
                                    System.out.println("| PUBLICATION YEAR. PLEASE TRY AGAIN              |");
                                    System.out.println("|-------------------------------------------------|");
                                }
                                if (loan.getReturnDate() != null) {
                                    if (loan.getLoanDate().after(loan.getReturnDate())) {
                                        System.out.println("|-------------------------------------------------|");
                                        System.out.println("| THE ENTERED DATE IS LATER THAN THE RETURN DATE.  |");
                                        System.out.println("| PLEASE TRY AGAIN                                |");
                                        System.out.println("|-------------------------------------------------|");
                                        bandera = true;
                                    }
                                }

                            } while (fechaP.getYear() < loan.getBook().getYear()
                                    || bandera);

                            break;
                        case 2:

                            //PEDIR LA FECHA DE RETORNO MIENTRAS QUE:
                            /*
                              - EL AÑO DE LA FECHA DE RETORNO SEA ANTES DEL AÑO DE PUBLICACION DEL LIBRO
                            O
                              - LA FECHA DE RETORNO SEA ANTES DE LA FECHA DEL PRESTAMO 
                             */
                            do {
                                System.out.print("   - Return Date (yyyy-mm-dd): ");
                                fechaP = LocalDate.parse(scaner.next());

                                loan.setReturnDate(java.sql.Date.valueOf(fechaP));
                                if (fechaP.getYear() < loan.getBook().getYear()) {

                                    System.out.println("|-------------------------------------------------|");
                                    System.out.println("| THE ENTERED YEAR IS EARLIER THAN BOOK'S         |");
                                    System.out.println("| PUBLICATION YEAR. PLEASE TRY AGAIN              |");
                                    System.out.println("|-------------------------------------------------|");
                                }
                                if (loan.getLoanDate().after(loan.getReturnDate())) {
                                    System.out.println("|-------------------------------------------------|");
                                    System.out.println("| THE ENTERED DATE IS EARLIER THAN THE LOAN DATE. |");
                                    System.out.println("| PLEASE TRY AGAIN                                |");
                                    System.out.println("|-------------------------------------------------|");
                                }

                            } while (fechaP.getYear() < loan.getBook().getYear()
                                    || loan.getLoanDate().after(loan.getReturnDate()));
                            break;
                        case 3:
                            //MOSTRAR LIBROS
                            servB.showBooks();

                            System.out.print("   - SELECT A BOOK ISBN: ");
                            loan.setBook(servB.selectABook(scaner.nextLong()));
                            break;
                        case 4:
                            //MOSTRAR LOS CLIENTES
                            servC.printClients();

                            System.out.print("   - SELECT A CLIENT ID: ");
                            loan.setClient(servC.slectClientID(scaner.nextInt()));

                            break;
                        default:
                            System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                            break;
                    }

                    if (opcM < 5) {
                        dao.update(loan);
                        System.out.println("|-------------------------------------------------|");
                        System.out.println("|  LOAN SUCCESSFULLY UPDATED FROM THE DATABASE    |");
                        System.out.println("|-------------------------------------------------|");
                    }
                } else {
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("| THE LOAN DOES NOT EXIST, PLEASE TRY AGAIN       |");
                    System.out.println("|-------------------------------------------------|");
                }
            }

        } catch (Exception e) {
            System.out.println("WRONGLY TYPED OPTION");
            scaner.nextLine();
        }
    }

    //OPCION 6 DEL MENU BUSCAR ------------------------------------------------
    public void searchLoanByClient() throws Exception {
        print4Opc6();
        System.out.print("   - CLIENT'S NAME: ");
        String nameC = scaner.next();

        List<Loan> loans = dao.selectLoanByClient(nameC);

        printLoansByFilter(loans);

        if (loans == null) {
            System.out.println("|-------------------------------------------------|");
            System.out.println("| MAYBE THE CLIENT DOES NOT EXIST,                |");
            System.out.println("| OR THE CLIENT DOES NOT HAVE LOANS. PLEASE TRY   |");
            System.out.println("| PLEASE TRY AGAIN                                |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //IMPRIMIR POR FILTRO ------------------------------------------------------
    private void printLoansByFilter(List<Loan> loans) {
        String vId = "__ ID __", vLD = "___ LOAN DATE ___", vRD = "___ RETURN DATE ___",
                vBook = "______ BOOK ______", vClient = "______ CLIENT ______", formarLD, formarRD;

        System.out.println("|--------------------------------------------------------------------------------------|");
        System.out.println("|                                         LOANS                                        |");
        System.out.println("|--------------------------------------------------------------------------------------|");
        System.out.println("|" + vId + "|" + vLD + "|" + vRD + "|" + vBook + "|" + vClient + "|");
        if (loans != null) {
            for (Loan aux : loans) {
                if (aux.getLoanDate() != null) {
                    formarLD = String.valueOf(aux.getLoanDate().getDate()) + " - "
                            + String.valueOf(aux.getLoanDate().getMonth() + 1) + " - "
                            + String.valueOf(aux.getLoanDate().getYear() + 1900);
                } else {
                    formarLD = " ";
                }

                if (aux.getReturnDate() != null) {
                    formarRD = String.valueOf(aux.getReturnDate().getDate()) + " - "
                            + String.valueOf(aux.getReturnDate().getMonth() + 1) + " - "
                            + String.valueOf(aux.getReturnDate().getYear() + 1900);
                } else {
                    formarRD = " ";
                }

                imprimirCasilla(String.valueOf(aux.getId()), vId);
                if (aux.getLoanDate() == null) {
                    imprimirCasilla(" ", vLD);
                } else {
                    imprimirCasilla(formarLD, vLD);
                }

                if (aux.getReturnDate() == null) {
                    imprimirCasilla(" ", vRD);
                } else {
                    imprimirCasilla(formarRD, vRD);
                }

                if (aux.getBook() == null) {
                    imprimirCasilla(" ", vBook);
                } else {
                    imprimirCasilla(aux.getBook().getTitle(), vBook);
                }

                if (aux.getClient() == null) {
                    imprimirCasilla(" ", vClient);
                } else {
                    imprimirCasilla(aux.getClient().getName(), vClient);
                }
                System.out.println("|");
            }
        } else {
            imprimirCasilla(" ", vId);
            imprimirCasilla(" ", vLD);
            imprimirCasilla(" ", vRD);
            imprimirCasilla(" ", vBook);
            imprimirCasilla(" ", vClient);
            System.out.println("|");
        }

        System.out.println("|--------------------------------------------------------------------------------------|");
    }

    //IMPRIMIR PRESTAMOS -------------------------------------------------------
    public void showLoans() throws Exception {
        String vId = "__ ID __", vLD = "___ LOAN DATE ___", vRD = "___ RETURN DATE ___",
                vBook = "______ BOOK ______", vClient = "______ CLIENT ______", formarLD, formarRD;

        //TRAER LOS REGISTROS DE LA TABLA
        List<Loan> loans = dao.selectLoan();

        System.out.println("|--------------------------------------------------------------------------------------|");
        System.out.println("|                                         LOANS                                        |");
        System.out.println("|--------------------------------------------------------------------------------------|");
        System.out.println("|" + vId + "|" + vLD + "|" + vRD + "|" + vBook + "|" + vClient + "|");
        if (loans != null) {
            for (Loan aux : loans) {
                if (aux.getLoanDate() != null) {
                    formarLD = String.valueOf(aux.getLoanDate().getDate()) + " - "
                            + String.valueOf(aux.getLoanDate().getMonth() + 1) + " - "
                            + String.valueOf(aux.getLoanDate().getYear() + 1900);
                } else {
                    formarLD = " ";
                }

                if (aux.getReturnDate() != null) {
                    formarRD = String.valueOf(aux.getReturnDate().getDate()) + " - "
                            + String.valueOf(aux.getReturnDate().getMonth() + 1) + " - "
                            + String.valueOf(aux.getReturnDate().getYear() + 1900);
                } else {
                    formarRD = " ";
                }

                imprimirCasilla(String.valueOf(aux.getId()), vId);
                if (aux.getLoanDate() == null) {
                    imprimirCasilla(" ", vLD);
                } else {
                    imprimirCasilla(formarLD, vLD);
                }

                if (aux.getReturnDate() == null) {
                    imprimirCasilla(" ", vRD);
                } else {
                    imprimirCasilla(formarRD, vRD);
                }

                if (aux.getBook() == null) {
                    imprimirCasilla(" ", vBook);
                } else {
                    imprimirCasilla(aux.getBook().getTitle(), vBook);
                }

                if (aux.getClient() == null) {
                    imprimirCasilla(" ", vClient);
                } else {
                    imprimirCasilla(aux.getClient().getName(), vClient);
                }
                System.out.println("|");
            }
        } else {
            imprimirCasilla(" ", vId);
            imprimirCasilla(" ", vLD);
            imprimirCasilla(" ", vRD);
            imprimirCasilla(" ", vBook);
            imprimirCasilla(" ", vClient);
            System.out.println("|");
        }

        System.out.println("|--------------------------------------------------------------------------------------|");

    }

}
