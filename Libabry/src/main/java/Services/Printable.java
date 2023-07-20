/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author irina
 */
public abstract class Printable {

    //MENU GENERAL
    protected void menu() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                     OPTIONS                     |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|  1.- INSERT                                     |");  //OK
        System.out.println("|  2.- DELETE                                     |");  //OK
        System.out.println("|  3.- UPDATE                                     |");  //OK
        System.out.println("|  4.- SEARCH                                     |");  //OK
        System.out.println("|  5.- SHOW BOOKS                                 |");  //OK
        System.out.println("|  6.- SHOW EDITORIALS                            |");  //OK
        System.out.println("|  7.- SHOW AUTHORS                               |");  //OK
        System.out.println("|  8.- SHOW CLIENTS                               |");  //OK
        System.out.println("|  9.- SHOW LOANS                                 |");  //OK
        System.out.println("| 10.- EXIT                                       |");
        System.out.println("|-------------------------------------------------|");
        System.out.print("   SELECT AN OPTION : ");
    }

    /* -------------------------------------------------------------------------------------------------------------
    OPCION 1 DEL MENU GENERAL
     */
    protected void minieMenuOpc1() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                 OPTIONS INSERT                  |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|  1.- INSERT AUTHOR                              |");  //OK
        System.out.println("|  2.- INSERT EDITORIAL                           |");  //OK
        System.out.println("|  3.- INSERT BOOK                                |");  //OK
        System.out.println("|  4.- INSERT CLIENT                              |");  //OK
        System.out.println("|  5.- INSERT LOAN                                |");  //OK 
        System.out.println("|  6.- EXIT                                       |");
        System.out.println("|-------------------------------------------------|");
        System.out.print("   SELECT AN OPTION : ");
    }

    //OPC 1 DEL INSERTAR
    protected void print1Opc1() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  INSERT AUTHOR                  |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPC 2 DEL INSERTAR
    protected void print1Opc2() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                INSERT EDITORIAL                 |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPC 3 DEL INSERTAR
    protected void print1Opc3() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                   INSERT BOOK                   |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPC 4 DEL INSERTAR
    protected void print1Opc4() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  INSERT CLIENT                  |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPC 5 DEL INSERTAR
    protected void print1Opc5() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  INSERT A LOAN                  |");
        System.out.println("|-------------------------------------------------|");
    }

    /* -------------------------------------------------------------------------------------------------------------
    OPCION 2 DEL MENU GENERAL
     */
    protected void minieMenuOpc2() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                 OPTIONS DELETE                  |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|  1.- DELETE AUTHOR                              |");  //OK
        System.out.println("|  2.- DELETE EDITORIAL                           |");  //OK
        System.out.println("|  3.- DELETE BOOK                                |");  //OK
        System.out.println("|  4.- DELETE CLIENT                              |");  //OK
        System.out.println("|  5.- DELETE LOAN                                |");  //OK
        System.out.println("|  6.- EXIT                                       |");
        System.out.println("|-------------------------------------------------|");
        System.out.print("   SELECT AN OPTION : ");
    }

    //OPCION 1 DEL ELIMINAR
    protected void print2Opc1() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  DELETE AUTHOR                  |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 2 DEL ELIMINAR
    protected void print2Opc2() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                 DELETE EDITORIAL                |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 3 DEL ELIMINAR
    protected void print2Opc3() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                   DELETE BOOK                   |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 4 DEL ELIMINAR
    protected void print2Opc4() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  DELETE CLIENT                  |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 5 DEL ELIMINAR
    protected void print2Opc5() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                   DELETE LOAN                   |");
        System.out.println("|-------------------------------------------------|");
    }

    /* -------------------------------------------------------------------------------------------------------------
    OPCION 3 DEL MENU GENERAL
     */
    protected void minieMenuOpc3() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                 OPTIONS UPDATE                  |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|  1.- UPDATE AUTHOR                              |");  //OK
        System.out.println("|  2.- UPDATE EDITORIAL                           |");  //OK
        System.out.println("|  3.- UPDATE BOOK                                |");  //OK
        System.out.println("|  4.- UPDATE CLIENT                              |");  //OK
        System.out.println("|  5.- UPDATE LOAN                                |");  //OK
        System.out.println("|  6.- EXIT                                       |");
        System.out.println("|-------------------------------------------------|");
        System.out.print("   SELECT AN OPTION : ");
    }

    //OPCION 1 DEL ACTUALIZAR
    protected void print3Opc1() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  UPDATE AUTHOR                  |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 2 DEL ACTUALIZAR
    protected void print3Opc2() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                 UPDATE EDITORIAL                |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 3 DEL ACTUALIZAR
    protected void print3Opc3() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                   UPDATE BOOK                   |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|    1. TITLE                                     |");
        System.out.println("|    2. YEAR                                      |");
        System.out.println("|    3. AUTHOR                                    |");
        System.out.println("|    4. EDITORIAL                                 |");
        System.out.println("|    5. COPIES                                    |");
        System.out.println("|    6. BORROWED COPIES                           |");
        System.out.println("|    7. REMAINING COPIES                          |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 4 DEL ACTUALIZAR
    protected void print3Opc4() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  UPDATE CLIENT                  |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|    1. NAME                                      |");
        System.out.println("|    2. LAST NAME                                 |");
        System.out.println("|    3. PHONE                                     |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 5 DEL ACTUALIZAR
    protected void print3Opc5() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  UPDATE LOAN                    |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|    1. LOAN DATE                                 |");
        System.out.println("|    2. RETURN DATE                               |");
        System.out.println("|    3. BOOK                                      |");
        System.out.println("|    4. CLIENT                                    |");
        System.out.println("|-------------------------------------------------|");
    }

    /* -------------------------------------------------------------------------------------------------------------
    OPCION 4 DEL MENU GENERAL
     */
    protected void minieMenuOpc4() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                 OPTIONS SEARCH                  |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|  1.- SEARCH AN AUTHOR BY NAME                   |");  //OK
        System.out.println("|  2.- SEARCH A BOOK BY ISBN                      |");  //OK
        System.out.println("|  3.- SEARCH A BOOK BY TITLE                     |");  //OK
        System.out.println("|  4.- SEARCH BOOKS BY AUTHOR                     |");  //OK
        System.out.println("|  5.- SEARCH BOOKS BY EDITORIAL                  |");  //OK
        System.out.println("|  6.- SEARCH LOANS BY CLIENT                     |");  //OK
        System.out.println("|  7.- EXIT                                       |");
        System.out.println("|-------------------------------------------------|");
        System.out.print("   SELECT AN OPTION : ");
    }

    //OPCION 1 DEL BUSCAR
    protected void print4Opc1() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|            SEARCH AN AUTHOR BY NAME             |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 2 DEL BUSCAR
    protected void print4Opc2() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|              SEARCH A BOOK BY ISBN              |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 3 DEL BUSCAR
    protected void print4Opc3() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|              SEARCH A BOOK BY TITLE             |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 4 DEL BUSCAR
    protected void print4Opc4() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|           SEARCH BOOKS BY AUTHOR'S NAME         |");
        System.out.println("|-------------------------------------------------|");
    }

    //OPCION 5 DEL BUSCAR
    protected void print4Opc5() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|             SEARCH BOOKS BY EDITORIAL           |");
        System.out.println("|-------------------------------------------------|");
    }
    
    //OPCION 6 DEL BUSCAR
    protected void print4Opc6() {
        System.out.println("|-------------------------------------------------|");
        System.out.println("|              SEARCH LOANS BY CLIENT             |");
        System.out.println("|-------------------------------------------------|");
    }

    // CASILLAS ----------------------------------------------------------------
    public void imprimirCasilla(String nombre, String variable) {
        int tamanio;
        String vAux;
        System.out.print("|");

        vAux = nombre;
        tamanio = vAux.length();

        if (tamanio % 2 == 0) {
            for (int i = 0; i < (variable.length() - tamanio) / 2; i++) {
                System.out.print(" ");
            }
            System.out.print(vAux);
            for (int i = 0; i < (variable.length() - tamanio + 1) / 2; i++) {
                System.out.print(" ");
            }
        } else {
            tamanio--;
            for (int i = 0; i < (variable.length() - tamanio) / 2; i++) {
                System.out.print(" ");
            }
            System.out.print(vAux);
            for (int i = 0; i < (variable.length() - tamanio - 1) / 2; i++) {
                System.out.print(" ");
            }
        }
    }
}
