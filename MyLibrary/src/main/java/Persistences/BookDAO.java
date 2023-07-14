/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistences;

import Entities.Book;
import java.util.List;

/**
 *
 * @author irina
 */
public class BookDAO extends DAO<Book> {

    //METODO INSERTAR LIBRO ----------------------------------------------------
    @Override
    public void insert(Book objeto) {
        super.insert(objeto);
    }

    //ELIMINAR LIBRO -----------------------------------------------------------
    public void delete(long idB) throws Exception {
        super.delete(selectBookByID(idB));
    }
    
    //ACTUALIZAR LIBRO ---------------------------------------------------------
    @Override
    public void update(Book objeto) {
        super.update(objeto); 
    }
    

    //ENCONTRAR UN LIBRO POR ID ------------------------------------------------
    public Book selectBookByID(long ID) throws Exception {
        try {
            //se conecta a la base de datos
            conect();

            //pedimos por medio de un query todos los autores
            Book book = em.find(Book.class, ID);

            //desconectamos de la base de datos
            desconect();

            //retornamos la list
            return book;
        } catch (Exception e) {
            throw new Exception("ERROR EN DAO BOOK, METODO selectEditorial: ", e);
        }
    }

    //DEVOLVER LA LISTA DE LIBROS -----------------------------------------
    public List<Book> selectBooks() throws Exception {
        try {
            //se conecta a la base de datos
            conect();

            //pedimos por medio de un query todos los autores
            List<Book> books = em.createQuery("SELECT b FROM Book b").getResultList();

            //desconectamos de la base de datos
            desconect();

            //retornamos la list
            return books;
        } catch (Exception e) {
            throw new Exception("ERROR EN DAO BOOK, METODO selectEditorial: ", e);
        }
    }

}
