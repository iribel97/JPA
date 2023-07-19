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
import Persistences.LoanDAO;
import java.util.List;
import java.util.Scanner;

public class ClientService extends Printable {

    private final ClientDAO dao;
    private final Scanner scaner = new Scanner(System.in);

    public ClientService() {
        this.dao = new ClientDAO();
    }

    //OPCION 4 DEL MENU INSERTAR
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

    //OPCION 4 DEL MENU ELIMINAR
    public void deleteClient() throws Exception {
        LoanDAO daoL = new LoanDAO();

        print2Opc4();

        printClients();

        //PEDIR AL USUARIO
        System.out.print("   - SELECT CLIENT ID OR 0 TO EXIT: ");
        int opc = scaner.nextInt();

        if (opc != 0) {
            //INSTANCIAMOS OBJETO DE TIPO CLIENT Y LE MANDAMOS EL ID QUE SELECCIONE EL USUARIO
            Client client = dao.selectClientByID(opc);

            if (client != null) {
                daoL.updateClientInNull(client.getId());
                dao.delete(client.getId());
                System.out.println("|-------------------------------------------------|");
                System.out.println("|  CLIENT SUCCESSFULLY DELETED FROM THE DATABASE  |");
                System.out.println("|-------------------------------------------------|");
            } else {
                System.out.println("|-------------------------------------------------|");
                System.out.println("| THE CLIENT DOES NOT EXIST, PLEASE TRY AGAIN     |");
                System.out.println("|-------------------------------------------------|");
            }
        }

    }

    //RETORNAR VALORES ---------------------------------------------------------
    /*VERIFICAR QUE EL CLIENTE EXISTA*/
    private boolean findClient(Client client) throws Exception {
        //TRAEMOS TODOS LOS CLIENTES 
        List<Client> clients = dao.selectClient();

        if (!clients.isEmpty()) {
            for (Client aux : clients) {
                if (aux.getDocument() == client.getDocument() || (aux.getName().equalsIgnoreCase(client.getName())
                        && aux.getLastName().equalsIgnoreCase(client.getLastName()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /*RETORNAR UN CLIENTE*/
    public Client slectClientID(int id) throws Exception {
        return dao.selectClientByID(id);
    }

    //IMPRIMIR CLIENTES --------------------------------------------------------
    public void printClients() throws Exception {
        String vID = "__ ID __", vD = "____ DOCUMENT ____", vName = "____ NAME ____",
                vLastN = "____ LAST NAME ____", vPhone = "____ # PHONE ____";

        //Intanciamos una lista que va a guardar lo que de el dao
        List<Client> clients = dao.selectClient();

        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|                                    CLIENTS                                   |");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|" + vID + "|" + vD + "|" + vName + "|" + vLastN + "|" + vPhone + "|");

        if (!clients.isEmpty()) {
            for (Client aux : clients) {
                imprimirCasilla(String.valueOf(aux.getId()), vID);
                imprimirCasilla(String.valueOf(aux.getDocument()), vD);
                imprimirCasilla(aux.getName(), vName);
                imprimirCasilla(aux.getLastName(), vLastN);
                imprimirCasilla(aux.getPhone(), vPhone);
                System.out.println("|");
            }
        } else {
            imprimirCasilla(" ", vID);
            imprimirCasilla(" ", vD);
            imprimirCasilla(" ", vName);
            imprimirCasilla(" ", vLastN);
            imprimirCasilla(" ", vPhone);
            System.out.println("|");
        }
        System.out.println("|--------------------------------------------------------------------------|");
    }
}
