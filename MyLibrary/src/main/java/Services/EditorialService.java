/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Editorial;
import Persistences.EditorialDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author irina
 */
public class EditorialService {
    Scanner scaner = new Scanner(System.in);
    EditorialDAO dao;

    public EditorialService() {
        this.dao = new EditorialDAO();
    }
    
    public void insertEditorial() throws Exception{
        boolean flag = false;
        
        List<Editorial> editoriales = dao.selectEditorial();
        
        Editorial editorial = new Editorial();
        
        System.out.print(" INGRESE EL NOMBRE DE LA EDITORIAL: ");
        editorial.setName(scaner.nextLine());
        
        if (!editoriales.isEmpty()) {
            for (Editorial aux : editoriales) {
                if (aux.getName().equalsIgnoreCase(editorial.getName())) {
                    flag = true;
                    System.out.println("EDITORIAL YA EXISTENTE");
                }
            }
        
        }
        
        if (!flag) {
            dao.insert(editorial);
        }
    }
    
    
    
    
}
