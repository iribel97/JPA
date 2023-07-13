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
public class AuthorService extends Printable{
    
    AuthorDAO dao;
    
    Scanner scaner = new Scanner(System.in);

    public AuthorService() {
        this.dao = new AuthorDAO();
    }
    
    public void insertAutor() throws Exception{
        
        boolean flag = false;
      
        List<Author> authors = dao.selectAutor();
       
        Author autor = new Author();
        printOpc1();
        System.out.print(" AUTHOR'S NAME: ");
        autor.setName(scaner.nextLine());
        
        //COMPROBAMOS QUE LA TABLA AUTOR EN LA BASE DE DATOS NO SE ENCUENTRA VACIA
        if (!authors.isEmpty()) {
            //BUCLE FOREACH PARA RECORRER LA TABLA
            for (Author aux : authors) {
                //COMPROBAR SI EL NOMBRE INGRESADO POR EL USUARIO EXISTE
                if (aux.getName().equalsIgnoreCase(autor.getName())) {
                    //CASO DE EXISTIR BOTA UN MENSAJE Y LA VARIABLE BOOLEANA SE VUELVE TRUE
                    flag = true;
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("|   AUTHOR ALREDY EXISTS                          |");
                    System.out.println("|-------------------------------------------------|");
                }
            }
        
        }
        
        //SI LA VARIABLE BOOLEANA SE MANTUVO FALSE, SIGNIFICA QUE EL USUARIO NO EXISTE
        if (!flag) {
            
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  AUTHOR SUCCESSFULLY ADDED TO THE DATABASE      |");
            System.out.println("|-------------------------------------------------|");
            //POR NO QUE PROCEDE A AGREGARLO A LA BASE DE DATOS
            dao.insert(autor);
        }
        
    }
    
    
}
