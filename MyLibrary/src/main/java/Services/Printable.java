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
    
    protected void menu(){
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                     OPTIONS                     |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|  1.- INSERT AUTHOR                              |");  //OK
        System.out.println("|  2.- INSERT EDITORIAL                           |");  //OK
        System.out.println("|  3.- INSERT BOOK                                |");  //OK
        System.out.println("|  4.- DELETE AUTHOR                              |");  //OK
        System.out.println("|  5.- DELETE EDITORIAL                           |");  //OK
        System.out.println("|  6.- DELETE BOOK                                |");  //OK
        System.out.println("|  7.- UPDATE AUTHOR                              |");
        System.out.println("|  8.- UPDATE EDITORIAL                           |");
        System.out.println("|  9.- UPDATE BOOK                                |");
        System.out.println("| 10.- SEARCH AN AUTHOR BY NAME                   |");
        System.out.println("| 11.- SEARCH A BOOK BY ISBN                      |");
        System.out.println("| 12.- SEARCH A BOOK BY TITLE                     |");
        System.out.println("| 13.- SEARCH BOOKS BY AUTHOR NAME                |");
        System.out.println("| 14.- SEARCH BOOKS BY EDITORIAL                  |");
        System.out.println("| 15.- SHOW BOOKS                                 |");
        System.out.println("| 16.- SHOW EDITORIALS                            |");  //OK
        System.out.println("| 17.- SHOW AUTHORS                               |");  //OK
        System.out.println("| 18.- EXIT                                       |");
        System.out.println("|-------------------------------------------------|");
        System.out.print("   SELECT AN OPTION : ");
    }
    
    protected void printOpc1(){
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  INSERT AUTHOR                  |");
        System.out.println("|-------------------------------------------------|");
    }
    
    protected void printOpc2(){
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                INSERT EDITORIAL                 |");
        System.out.println("|-------------------------------------------------|");
    }
    
    protected void printOpc3(){
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                   INSERT BOOK                   |");
        System.out.println("|-------------------------------------------------|");
    }
    
    protected void printOpc4(){
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                  DELETE AUTHOR                  |");
        System.out.println("|-------------------------------------------------|");
    }
    
    protected void printOpc5(){
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                 DELETE EDITORIAL                |");
        System.out.println("|-------------------------------------------------|");
    }
    
    protected void printOpc6(){
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                   DELETE BOOK                   |");
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
            for (int i = 0; i < (variable.length() - tamanio+1) / 2; i++) {
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
