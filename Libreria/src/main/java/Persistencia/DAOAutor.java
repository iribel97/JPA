/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidad.Autor;
import java.util.List;

/**
 *
 * @author irina
 */
public class DAOAutor extends DAO<Autor> {

    //METODO INSERTAR AUTOR ----------------------------------------------------
    @Override
    public void guardar(Autor objeto) {
        super.guardar(objeto);
    }

    //METODO PARA ELIMINAR UN AUTOR --------------------------------------------
    public void eliminar(int idAutor) {
        super.eliminar(em.find(Autor.class, idAutor));
    }

    //METODO PARA MODIFICAR ----------------------------------------------------
    @Override
    public void editar(Autor objeto) {
        super.editar(objeto);
    }

    //ENCONTRAR UN AUTOR POR ID ------------------------------------------------
    public Autor selectAutorByID(int idAutor) throws Exception {
        try {
            //Caso de que idAutor sea null
            if (idAutor == 0) {
                throw new Exception("Debe de especificar el identificador del autor");
            }
            
            //CONECTARSE A LA BASE DE DATOS
            conectar();

            //BUSCAR EL AUTOR
            Autor autor = em.find(Autor.class, idAutor);

            //UNA VEZ QUE SE OPTIENE EL AUTOR SE DESCONECTA LA BASE DE DATOS
            desconectar();

            //RETORNAMOS EL AUTOR
            return autor;
        } catch (Exception e) {
            throw new Exception("ERROR EN DAO AUTOR, METODO selectAutorByID: ", e);
        }

    }
    
    //SELECCIONAR TODOS LOS AUTORES DE LA TABLA --------------------------------
    public List<Autor> selectAutor() throws Exception{
        try {
            //se conecta a la base de datos
            conectar();
            
            //pedimos por medio de un query todos los autores
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
            
            //desconectamos de la base de datos
            desconectar();
            
            //retornamos la list
            return autores;
        } catch (Exception e) {
            throw new Exception("EEROR EN DAO AUTOR, METODO selectAutor: ", e);
        }
    }
    

}
