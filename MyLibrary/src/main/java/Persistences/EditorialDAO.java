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
