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
public class BookDAO extends DAO<Book>{

    @Override
    protected void insert(Book objeto) {
        super.insert(objeto);
    }
    
    //DEVOLVER LA LISTA DE LIBROS -----------------------------------------
    public List<Book> selectEditorial() throws Exception{
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
            throw new Exception("ERROR EN DAO EDITORIAL, METODO selectEditorial: ", e);
        }
    }
    
}
