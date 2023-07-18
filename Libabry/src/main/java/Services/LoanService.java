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
    private final Scanner scaner = new Scanner(System.in);

    public LoanService() {
        this.dao = new LoanDAO();
    }

    public void insertLoan() {
        LocalDate fechaP;
        //INSTANCIAR OBJETO DE TIPO PRESTAMO
        Loan loan = new Loan();

        print1Opc5();
        //Loan(int id, Date loanDate, Date returnDate, Book book, Client client)
        System.out.print("   - Loan Date (yyyy-mm-dd): ");
        fechaP = LocalDate.parse(scaner.next());
        loan.setLoanDate(java.sql.Date.valueOf(fechaP));
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

}
