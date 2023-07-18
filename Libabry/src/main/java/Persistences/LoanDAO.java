/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistences;

import Entities.Loan;
import java.util.List;

/**
 *
 * @author irina
 */
public class LoanDAO extends DAO<Loan>{

    //METODO INSERTAR PRESTAMI -------------------------------------------------
    @Override
    protected void insert(Loan objeto) {
        super.insert(objeto); 
    }
    
    //LISTADO DE TODOS LOS PRESTAMOS -------------------------------------------
    public List<Loan> selectLoan() throws Exception{
        try {

            //se conecta a la base de datos
            conect();

            //pedimos por medio de un query todos los clientes
            List<Loan> loans = em.createQuery("SELECT l FROM Loan l").getResultList();

            //desconectamos de la base de datos
            desconect();

            //retornamos la list
            return loans;
        } catch (Exception e) {
            throw new Exception("ERROR IN DAO AUTHOR, METHOD selectClient: ", e);
        }
    }
    
}
