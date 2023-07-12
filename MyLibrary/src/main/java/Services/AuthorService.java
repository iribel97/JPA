/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Author;
import Persistences.AuthorDAO;
import java.util.Scanner;

/**
 *
 * @author irina
 */
public class AuthorService {
    
    AuthorDAO dao;
    
    Scanner scaner = new Scanner(System.in);

    public AuthorService() {
        this.dao = new AuthorDAO();
    }
    
    public void insertAutor(){

        Author autor = new Author();
        
        System.out.print(" INGRESE EL NOMBRE DEL AUTOR: ");
        autor.setName(scaner.nextLine());
        
        dao.insert(autor);
        
    }
    
    
}
