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
        System.out.println("|  2.- INSERT EDITORIAL                           |");
        System.out.println("|  3.- INSERT BOOK                                |");
        System.out.println("|  4.- DELETE AUTHOR                              |");
        System.out.println("|  5.- DELETE EDITORIAL                           |");
        System.out.println("|  6.- DELETE BOOK                                |");
        System.out.println("|  7.- UPDATE AUTHOR                              |");
        System.out.println("|  8.- UPDATE EDITORIAL                           |");
        System.out.println("|  9.- UPDATE BOOK                                |");
        System.out.println("| 10.- SEARCH AN AUTHOR BY NAME                   |");
        System.out.println("| 11.- SEARCH A BOOK BY ISBN                      |");
        System.out.println("| 12.- SEARCH A BOOK BY TITLE                     |");
        System.out.println("| 13.- SEARCH BOOKS BY AUTHOR NAME                |");
        System.out.println("| 14.- SEARCH BOOKS BY EDITORIAL                  |");
        System.out.println("| 15.- EXIT                                       |");
        System.out.println("|-------------------------------------------------|");
        System.out.print("   SELECT AN OPTION : ");
    }
    
}
