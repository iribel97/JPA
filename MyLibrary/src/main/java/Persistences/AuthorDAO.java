/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistences;

import Entities.Author;
import java.util.List;

/**
 *
 * @author irina
 */
public class AuthorDAO extends DAO<Author> {

    //METODO INSERTAR AUTOR ----------------------------------------------------
    @Override
    public void insert(Author objeto) {
        super.insert(objeto);
    }

    //METODO PARA ELIMINAR UN AUTOR --------------------------------------------
    public void delete(int idAuthor) throws Exception {
        super.delete(selectAutorByID(idAuthor));
    }

    //METODO PARA MODIFICAR ----------------------------------------------------
    @Override
    public void update(Author objeto) {
        super.update(objeto);

    }

    //ENCONTRAR UN AUTOR POR ID ------------------------------------------------
    public Author selectAutorByID(int idAutor) throws Exception {
        try {
            //Caso de que idAutor sea null
            if (idAutor == 0) {
                throw new Exception("Debe de especificar el identificador del autor");
            }

            //CONECTARSE A LA BASE DE DATOS
            conect();

            //BUSCAR EL AUTOR
            Author autor = em.find(Author.class, idAutor);

            //UNA VEZ QUE SE OPTIENE EL AUTOR SE DESCONECTA LA BASE DE DATOS
            desconect();

            //RETORNAMOS EL AUTOR
            return autor;
        } catch (Exception e) {
            throw new Exception("ERROR EN DAO AUTOR, METODO selectAutorByID: ", e);
        } finally {
            desconect();
        }
    }

    //SELECCIONAR TODOS LOS AUTORES DE LA TABLA --------------------------------
    public List<Author> selectAutor() throws Exception {
        try {

            //se conecta a la base de datos
            conect();

            //pedimos por medio de un query todos los autores
            List<Author> autores = em.createQuery("SELECT a FROM Author a").getResultList();

            //desconectamos de la base de datos
            desconect();

            //retornamos la list
            return autores;
        } catch (Exception e) {
            throw new Exception("ERROR IN DAO AUTHOR, METHOD selectAutor: ", e);
        }
    }

    //SELECCIONAR UN AUTOR POR EL NOMBRE ---------------------------------------
    public List<Author> selectAuthorByName(String name) throws Exception {
        try {
            //se conecta a la base de datos
            conect();
            
            //por medio de un query pedimos que nos regrese un autor
            List<Author> author = em.createQuery("SELECT a FROM Author a WHERE a.name LIKE :nameA")
            .setParameter("nameA", "%" + name + "%")
            .getResultList();
            
            //desconectamos de la base de datos
            desconect();

            //retornamos el autor
            return author;
        } catch (Exception e) {
            throw new Exception("ERROR IN DAO AUTHOR, METHOD selectAuthorByName");
        }
    }
}
