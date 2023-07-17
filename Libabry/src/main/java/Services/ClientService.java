/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author irina
 */
import Entities.Client;
import Persistences.ClientDAO;
import java.util.List;
import java.util.Scanner;

public class ClientService extends Printable {

    private final ClientDAO dao;
    private final Scanner scaner = new Scanner(System.in);

    public ClientService() {
        this.dao = new ClientDAO();
    }

    public void insertClient() throws Exception {
        Client client = new Client();

        print1Opc4();

        //public Client(long document, String name, String lastName, String phone)
        System.out.print("   - DOCUMENT: ");
        client.setDocument(scaner.nextLong());
        System.out.print("   - NAME:");
        client.setName(scaner.next());
        System.out.print("   - LAST NAME: ");
        client.setLastName(scaner.next());
        System.out.print("   - PHONE: ");
        client.setPhone(scaner.next());

        //SI EL METODO BOOLEANO SE MANTUVO FALSE, SIGNIFICA QUE EL USUARIO NO EXISTE
        if (!findClient(client)) {
            dao.insert(client);
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  CLIENT SUCCESSFULLY ADDED TO THE DATABASE      |");
            System.out.println("|-------------------------------------------------|");
        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|   CLIENT ALREDY EXISTS                          |");
            System.out.println("|-------------------------------------------------|");
        }

    }

    //RETORNAR VALORES ---------------------------------------------------------
    /*VERIFICAR QUE EL CLIENTE EXISTA*/
    private boolean findClient(Client client) throws Exception {
        //TRAEMOS TODOS LOS CLIENTES 
        List<Client> clients = dao.selectClient();

        if (!clients.isEmpty()) {
            for (Client aux : clients) {
                if (aux.getDocument() == client.getDocument()) {
                    return true;
                }
            }
        }
        return false;
    }

}
