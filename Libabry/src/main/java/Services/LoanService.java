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
        System.out.print("   - Loan Date (yyyy-mm-dd): ");
        fechaP = LocalDate.parse(scaner.next());
        loan.setLoanDate(java.sql.Date.valueOf(fechaP));
        System.out.print("   - DO YOU WANT TO INSERT THE RETURN DATE? (y/n): ");
        String opcS = scaner.next().toLowerCase();
        if (opcS.charAt(0) == 'y') {
            do {
                System.out.print("   - Return Date (yyyy-mm-dd): ");
                fechaP = LocalDate.parse(scaner.next());
                loan.setReturnDate(java.sql.Date.valueOf(fechaP));
                if (loan.getLoanDate().after(loan.getReturnDate())) {
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("| WRONG, TRY AGAIN                                |");
                    System.out.println("|-------------------------------------------------|");
                }
            } while (loan.getLoanDate().after(loan.getReturnDate()));
        }

        //Mostrar los libros para que el usuario seleccione
        servB.showBooks();
        //Pedir al usuario que escoja el ISBN del libro o seleccione 0 para no insertar ni uno
        System.out.print("   - SELECT A BOOK ISBN OR 0 TO EXIT: ");
        long isbn = scaner.nextLong();
        /*si la seleccion del usuario no es 0, se procede a setear el atributo LIBRO por el que el 
        usuario selecciono*/
        if (isbn != 0) {
            loan.setBook(servB.selectABook(isbn));
        }

        //Mostrar clientes para que el usuario seleccione
        servC.showClients();
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

    //OPCION 5 DEL MENU ELIMINAR
    public void deleteLoan() throws Exception {

        print2Opc5();
        
        showLoans();
        
        //PEDIR AL USUARIO
        System.out.print("   - SELECT LOAN ID: ");
        
        Loan loan = dao.selectLoanByID(scaner.nextInt());
        
        if (loan != null) {
            dao.delete(loan.getId());
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  LOAN SUCCESSFULLY DELETED FROM THE DATABASE    |");
            System.out.println("|-------------------------------------------------|");
        } else{
            System.out.println("|-------------------------------------------------|");
            System.out.println("| THE LOAN DOES NOT EXIST, PLEASE TRY AGAIN       |");
            System.out.println("|-------------------------------------------------|");
        }

    }

    //IMPRIMIR PRESTAMOS -------------------------------------------------------
    public void showLoans() throws Exception {
        String vId = "__ ID __", vLD = "___ LOAN DATE ___", vRD = "___ RETURN DATE ___",
                vBook = "______ BOOK ______", vClient = "______ CLIENT ______";

        //TRAER LOS REGISTROS DE LA TABLA
        List<Loan> loans = dao.selectLoan();

        System.out.println("|--------------------------------------------------------------------------------------|");
        System.out.println("|                                         LOANS                                        |");
        System.out.println("|--------------------------------------------------------------------------------------|");
        System.out.println("|" + vId + "|" + vLD + "|" + vRD + "|" + vBook + "|" + vClient + "|");

        for (Loan aux : loans) {
            String formarLD = String.valueOf(aux.getLoanDate().getDate()-1) + " - "
                    + String.valueOf(aux.getLoanDate().getMonth()+1) + " - " + 
                    String.valueOf(aux.getLoanDate().getYear() + 1900);
    
            String formarRD = String.valueOf(aux.getReturnDate().getDate()-1) + " - "
                    + String.valueOf(aux.getReturnDate().getMonth()+1) + " - " + 
                    String.valueOf(aux.getReturnDate().getYear() + 1900);
            
            
            imprimirCasilla(String.valueOf(aux.getId()), vId);
            if (aux.getLoanDate() == null) {
                imprimirCasilla(" ", vLD);
            }else{
                imprimirCasilla(formarLD, vLD);
            }
            
            if (aux.getReturnDate() == null) {
                imprimirCasilla(" ", vRD);
            }else{
                imprimirCasilla(formarRD, vRD);
            }
            
            if (aux.getBook() == null) {
                imprimirCasilla(" ", vBook);
            }else{
                imprimirCasilla(aux.getBook().getTitle(), vBook);
            }
            
            if (aux.getClient() == null) {
                imprimirCasilla(" ", vClient);
            }else{
                imprimirCasilla(aux.getClient().getName(), vClient);
            }
            System.out.println("|");
        }
        System.out.println("|--------------------------------------------------------------------------------------|");
    }

}
