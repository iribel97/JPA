/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistences;

import Entities.Client;
import Entities.Loan;
import java.util.List;

/**
 *
 * @author irina
 */
public class LoanDAO extends DAO<Loan> {

    //METODO INSERTAR PRESTAMO -------------------------------------------------
    @Override
    public void insert(Loan objeto) {
        super.insert(objeto);
    }

    //METODO ELIMINAR PRESTAMO -------------------------------------------------
    public void delete(int ID) throws Exception {
        super.delete(selectLoanByID(ID));
    }

    //METODO ACTUALIZAR PRESTAMO -----------------------------------------------
    @Override
    public void update(Loan objeto) {
        super.update(objeto);
    }

    //CAMBIAR DE PARAMETRO DE LIBRO EN NULL CUANDO SE ELIMINE UN LIBRO QUE SE ENCUENTRE EN LA TABLA PRESTAMO
    public void updateBookInNull(Long idBook) throws Exception {
        try {

            //Traemos toda los registros de la tabla prestamo
            List<Loan> loans = selectLoan();

            //Recorremos la lista
            for (Loan aux : loans) {
                if (aux.getBook().getIsbn() == idBook) {
                    aux.setBook(null);
                    update(aux);
                }
            }

        } catch (Exception e) {
            throw new Exception("ERROR IN DAO LOAN, METHOD updateBookInNull: ", e);
        }
    }

    //CAMBIAR DE PARAMETRO DE CLIENTE EN NULL CUANDO SE ELIMINE UN CLIENTE QUE SE ENCUENTRE EN LA TABLA PRESTAMO
    public void updateClientInNull(int idClient) throws Exception {
        try {

            //Traemos toda los registros de la tabla prestamo
            List<Loan> loans = selectLoan();

            //Recorremos la lista
            for (Loan aux : loans) {
                if (aux.getClient().getId() == idClient) {
                    aux.setClient(null);
                    update(aux);
                }
            }

        } catch (Exception e) {
            throw new Exception("ERROR IN DAO LOAN, METHOD updateBookInNull: ", e);
        }
    }

    //RETORNAR PRESTAMO POR ID -------------------------------------------------
    public Loan selectLoanByID(int idLoan) throws Exception {
        try {

            //se conecta a la base de datos
            conect();

            //pedimos por medio de un query todos los clientes
            Loan loan = em.createQuery("SELECT l FROM Loan l WHERE l.id = :id", Loan.class).
                    setParameter("id", idLoan).getSingleResult();

            //desconectamos de la base de datos
            desconect();

            //retornamos la list
            return loan;
        } catch (Exception e) {
            throw new Exception("ERROR IN DAO LOAN, METHOD selectLoanByID: ", e);
        }

    }

    //LISTADO DE TODOS LOS PRESTAMOS -------------------------------------------
    public List<Loan> selectLoan() throws Exception {
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
            throw new Exception("ERROR IN DAO LOAN, METHOD selectLoan: ", e);
        }
    }

    //SELECCIONAR LIBROS POR AUTOR ---------------------------------------------
    public List<Loan> selectLoanByClient(String nombreClient) throws Exception {
        try {
            ClientDAO daoC = new ClientDAO();

            Client client = daoC.selectClientByName(nombreClient);

            if (client != null) {

                //se conecta a la base de datos
                conect();

                //pedimos por medio de un query todos los clientes
                List<Loan> loans = em.createQuery("SELECT l FROM Loan l WHERE l.client = :client", Loan.class)
                        .setParameter("client", client)
                        .getResultList();

                //desconectamos de la base de datos
                desconect();

                //retornamos la list
                return loans;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("ERROR IN DAO LOAN, METHOD selectLoanByClient: ", e);
        }
    }

}
