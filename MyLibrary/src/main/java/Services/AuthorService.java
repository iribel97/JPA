/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Author;
import Persistences.AuthorDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author irina
 */
public class AuthorService extends Printable {

    private final AuthorDAO dao;

    private final Scanner scaner = new Scanner(System.in);

    public AuthorService() {
        this.dao = new AuthorDAO();
    }

    //OPCION 1 DEL MENU
    public void insertAutor() throws Exception {

        Author autor = new Author();
        printOpc1();
        System.out.print(" AUTHOR'S NAME: ");
        autor.setName(scaner.nextLine());

        //SI LA VARIABLE BOOLEANA SE MANTUVO FALSE, SIGNIFICA QUE EL USUARIO NO EXISTE
        if (!findAuthor(autor)) {
            //POR LO QUE PROCEDE A AGREGARLO A LA BASE DE DATOS
            dao.insert(autor);
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  AUTHOR SUCCESSFULLY ADDED TO THE DATABASE      |");
            System.out.println("|-------------------------------------------------|");

        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|   AUTHOR ALREDY EXISTS                          |");
            System.out.println("|-------------------------------------------------|");
        }

    }

    //OPCION 4 DEL MENU
    public void deletAuthor() throws Exception {

        printOpc4();
        showAuthors();
        System.out.print("   - SELECT THE AUTHOR ID: ");
        //INSTANCIAMOS UN OBJETO DE TIPO AUTOR
        Author autor = dao.selectAutorByID(scaner.nextInt());
        //VERIFICAR QUE EL USUARIO EXISTA
        if (autor != null) {
            dao.delete(autor.getId());
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  AUTHOR SUCCESSFULLY DELETED FROM THE DATABASE  |");
            System.out.println("|-------------------------------------------------|");
        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  THE AUTHOR DOES NOT EXIST, PLEASE TRY AGAIN    |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //OPCION 7 DEL MENU
    public void updateAuthor() throws Exception {
        printOpc7();
        showAuthors();
        System.out.print("   - SELECT THE AUTHOR ID: ");
        //INSTANCIAMOS UN OBJETO DE TIPO AUTOR
        Author autor = dao.selectAutorByID(scaner.nextInt());
        if (autor != null) {
            System.out.println("|-------------------------------------------------|");
            scaner.nextLine();
            System.out.print("    - AUTHOR'S NAME: ");
            autor.setName(scaner.nextLine());
            dao.update(autor);
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  AUTHOR SUCCESSFULLY UPDATED FROM THE DATABASE  |");
            System.out.println("|-------------------------------------------------|");
        }else{
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  THE AUTHOR DOES NOT EXIST, PLEASE TRY AGAIN    |");
            System.out.println("|-------------------------------------------------|");
        }

    }

    //IMPRIMIR AUTORES ---------------------------------------------------------
    public void showAuthors() throws Exception {
        List<Author> authors = dao.selectAutor();
        String vID = "___ ID ___", vName = "________________ NAME ________________";
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                     AUTHORS                     |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|" + vID + "|" + vName + "|");
        for (Author aux : authors) {
            imprimirCasilla(String.valueOf(aux.getId()), vID);
            imprimirCasilla(aux.getName(), vName);
            System.out.println("|");
        }
        System.out.println("|-------------------------------------------------|");
    }

    //RETORNAR VALORES ---------------------------------------------------------
    /*RETORNAR UN AUTOR*/
    public Author selectOneAuthor(int idA) throws Exception {
        return dao.selectAutorByID(idA);
    }

    /*VERIFICAR QUE EL AUTOR EXISTA*/
    private boolean findAuthor(Author author) throws Exception {
        List<Author> authors = dao.selectAutor();
        if (!authors.isEmpty()) {
            for (Author aux : authors) {
                if (aux.getName().equalsIgnoreCase(author.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

}
