/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistences;

import Entities.Editorial;
import java.util.List;

/**
 *
 * @author irina
 */
public class EditorialDAO extends DAO<Editorial>{

    @Override
    public void insert(Editorial objeto) {
        super.insert(objeto);
    }
    
    //ENCONTRAR UN EDITORIAL POR ID --------------------------------------------
    public Editorial selectEditorialByID(int idEditorial) throws Exception{
        try {
            //Caso de que idAutor sea null
            if (idEditorial == 0) {
                throw new Exception("Debe de especificar el identificador del autor");
            }
            
            //CONECTARSE A LA BASE DE DATOS
            conect();

            //BUSCAR EL AUTOR
            Editorial editorial = em.find(Editorial.class, idEditorial);

            //UNA VEZ QUE SE OPTIENE EL AUTOR SE DESCONECTA LA BASE DE DATOS
            desconect();

            //RETORNAMOS EL AUTOR
            return editorial;
        } catch (Exception e) {
            throw new Exception("ERROR EN DAO AUTOR, METODO selectAutorByID: ", e);
        }
    }
    
    //DEVOLVER LA LISTA DE EDITORIALES -----------------------------------------
    public List<Editorial> selectEditorial() throws Exception{
        try {
            //se conecta a la base de datos
            conect();
            
            //pedimos por medio de un query todos los autores
            List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
            
            //desconectamos de la base de datos
            desconect();
            
            //retornamos la list
            return editoriales;
        } catch (Exception e) {
            throw new Exception("ERROR EN DAO EDITORIAL, METODO selectEditorial: ", e);
        }
    }
}
