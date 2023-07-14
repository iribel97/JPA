/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistences;

import Entities.Author;
import Entities.Book;
import Entities.Editorial;
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

    //DEVOLVER LIBROS POR TITULO -----------------------------------------------
    public List<Book> selectBookByTitle(String name) throws Exception {
        try {

            //se conecta a la base de datos
            conect();

            //pedimos por medio de un query todos los autores
            List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.title LIKE :nombreA", Book.class)
                    .setParameter("nombreA", "%" + name + "%")
                    .getResultList();

            //desconectamos de la base de datos
            desconect();

            //retornamos la list
            return books;

        } catch (Exception e) {
            throw new Exception("ERROR EN DAO BOOK, METODO selectBookByTitle: ", e);
        }
    }

    //DEVOLVER LIBROS POR NOMBRE DE AUTOR --------------------------------------
    public List<Book> selectBookByAuthor(String nombre) throws Exception {
        try {
            //SE INSTANCIA EL DAO DEL AUTOR
            AuthorDAO daoA = new AuthorDAO();
            //SE INSTANCIA UN AUTOR PARA QUE SE GUARDE CON EL NOMBRE QUE PASA POR PARAMETRO EL USUARIO
            Author servA = daoA.selectAnAuthorByName(nombre);
            
            //SI NO ES NULO
            if (servA != null) {
                //se conecta a la base de datos
                conect();

                //pedimos por medio de un query todos los autores
                List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.author = :nombreA", Book.class)
                        .setParameter("nombreA", servA)
                        .getResultList();
                //desconectamos de la base de datos
                desconect();

                //retornamos la list
                return books;
            }else{
                //EN CASO DE QUE SEA NULO, DEVOLVERA NULO
                return null;
            }

        } catch (Exception e) {
            throw new Exception("ERROR EN DAO BOOK, METODO selectBookByTitle: ", e);
        }
    }
    
    //DEVOLVER LIBROS POR EDITORIAL --------------------------------------------
    public List<Book> selectBookByEditorial(String nombre) throws Exception {
        try {
            //SE INSTANCIA EL DAO DEL AUTOR
            EditorialDAO daoE = new EditorialDAO();
            //SE INSTANCIA UN AUTOR PARA QUE SE GUARDE CON EL NOMBRE QUE PASA POR PARAMETRO EL USUARIO
            Editorial servE = daoE.selectEditorialByName(nombre);
            
            //SI NO ES NULO
            if (servE != null) {
                //se conecta a la base de datos
                conect();

                //pedimos por medio de un query todos los autores
                List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.editorial = :nombreE", Book.class)
                        .setParameter("nombreE", servE)
                        .getResultList();
                //desconectamos de la base de datos
                desconect();

                //retornamos la list
                return books;
            }else{
                //EN CASO DE QUE SEA NULO, DEVOLVERA NULO
                return null;
            }

        } catch (Exception e) {
            throw new Exception("ERROR EN DAO BOOK, METODO selectBookByTitle: ", e);
        }
    }

}
