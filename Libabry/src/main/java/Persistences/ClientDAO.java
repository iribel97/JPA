/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistences;

import Entities.Client;
import java.util.List;

/**
 *
 * @author irina
 */
public class ClientDAO extends DAO<Client> {

    //METODO INSERTAR CLIENTE --------------------------------------------------
    @Override
    public void insert(Client objeto) {
        super.insert(objeto);
    }
    
    //METODO ELIMINAR CLIENTE --------------------------------------------------
    public void delete(int idClient) throws Exception {
        super.delete(selectClientByID(idClient));
    }
    
    //METODO ACTUALIZAR CLIENTE ------------------------------------------------

    @Override
    public void update(Client objeto) {
        super.update(objeto);
    }
    
    

    //SELECCIONAR CLIENTE POR ID -----------------------------------------------
    public Client selectClientByID(int idC) throws Exception {
        try {
            //Caso de que idC sea null
            if (idC == 0) {
                throw new Exception("Debe de especificar el identificador del cliente");
            }

            //se conecta a la base de datos
            conect();

            //BUSCAR EL CLIENTE
            Client client = em.find(Client.class, idC);

            desconect();

            return client;

        } catch (Exception e) {
            throw new Exception("ERROR IN DAO CLIENT, METHOD selectClientByID: ", e);
        }
    }

    //LISTADO DE TODOS LOS CLIENTES --------------------------------------------
    public List<Client> selectClient() throws Exception {
        try {

            //se conecta a la base de datos
            conect();

            //pedimos por medio de un query todos los clientes
            List<Client> clients = em.createQuery("SELECT c FROM Client c").getResultList();

            //desconectamos de la base de datos
            desconect();

            //retornamos la list
            return clients;
        } catch (Exception e) {
            throw new Exception("ERROR IN DAO CLIENT, METHOD selectClient: ", e);
        }
    }

}
