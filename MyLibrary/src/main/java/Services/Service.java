/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.util.Scanner;

/**
 *
 * @author irina
 */
public class Service extends Printable {

    Scanner scaner = new Scanner(System.in);

    public Service() {
    }

    public boolean menuServ() throws Exception {
        int opc = 0;
        AuthorService servA = new AuthorService();
        EditorialService servE = new EditorialService();
        BookService servB = new BookService();

        //Repetir el menu mientras la opc digitada sea mayor de 14
        do {
            try {
                menu();
                opc = scaner.nextInt();

                if (opc > 15) {
                    System.out.println("OPTION DOES NOT EXIST, TRY AGAIN");
                }
            } catch (Exception e) {
                opc = 16;
                System.out.println("WRONGLY TYPED OPTION");
                scaner.nextLine();

            } 
        } while (opc > 15);

        switch (opc) {
            case 1:
                servA.insertAutor();
                break;
            case 2:
                servE.insertEditorial();
                break;
            case 3:
                servB.insertBook();
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:

                break;
            case 11:

                break;
            case 12:

                break;
            case 13:

                break;

        }

        return opc != 15;
    }

}
