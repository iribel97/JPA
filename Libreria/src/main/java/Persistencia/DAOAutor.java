/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidad.Autor;

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
    public Autor slsectAutorByID(int idAutor){
        //CONECTARSE A LA BASE DE DATOS
        conectar();
        
        //BUSCAR EL AUTOR
        Autor autor = em.find(Autor.class, idAutor);
        
        //UNA VEZ QUE SE OPTIENE EL AUTOR SE DESCONECTA LA BASE DE DATOS
        desconectar();
        
        //RETORNAMOS EL AUTOR
        return autor;
    }

}
