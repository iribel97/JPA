/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Loan;
import Persistences.LoanDAO;
import java.time.LocalDate;
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
        System.out.print("   - SELECT A BOOK ISBN OR 0 TO EXIT: ");
        long isbn = scaner.nextLong();
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

        System.out.println("|-------------------------------------------------|");
        System.out.println("|  LOAN SUCCESSFULLY ADDED TO THE DATABASE        |");
        System.out.println("|-------------------------------------------------|");
        dao.insert(loan);
    }

}
