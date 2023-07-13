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
      
        System.out.print(" INGRESE EL NOMBRE DEL AUTOR: ");
        autor.setName(scaner.nextLine());
        
        if (!authors.isEmpty()) {
            for (Author aux : authors) {
                if (aux.getName().equalsIgnoreCase(autor.getName())) {
                    flag = true;
                    System.out.println("AUTOR YA EXISTENTE");
                }
            }
        
        }
        
        if (!flag) {
            dao.insert(autor);
        }
        
    }
    
    
}
