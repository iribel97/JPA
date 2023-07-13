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
public class EditorialService extends Printable{
    Scanner scaner = new Scanner(System.in);
    EditorialDAO dao;

    public EditorialService() {
        this.dao = new EditorialDAO();
    }
    
    public void insertEditorial() throws Exception{
        boolean flag = false;
        
        List<Editorial> editoriales = dao.selectEditorial();
        
        Editorial editorial = new Editorial();
        
        printOpc2();
        
        System.out.print(" EDITORIAL'S NAME: ");
        editorial.setName(scaner.nextLine());
        
        if (!editoriales.isEmpty()) {
            for (Editorial aux : editoriales) {
                if (aux.getName().equalsIgnoreCase(editorial.getName())) {
                    flag = true;
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("|   EDITORIAL ALREDY EXISTS                       |");
                    System.out.println("|-------------------------------------------------|");
                }
            }
        
        }
        
        if (!flag) {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  EDITORIAL SUCCESSFULLY ADDED TO THE DATABASE   |");
            System.out.println("|-------------------------------------------------|");
            dao.insert(editorial);
        }
    }
    
    //IMPRIMIR EDITORIALES ---------------------------------------------------------
    public void showEditorials() throws Exception{
        List<Editorial> editoriales = dao.selectEditorial();
        String vID = "___ ID ___", vName = "________________ NAME ________________";
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                    EDITORIALS                   |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|"+vID+"|"+vName+"|");
        for(Editorial aux : editoriales){
            imprimirCasilla(String.valueOf(aux.getId()), vID);
            imprimirCasilla(aux.getName(), vName);
            System.out.println("|");
        }
        System.out.println("|-------------------------------------------------|");
    }
    
    
    //RETORNAR VALORES ---------------------------------------------------------
    
    /*RETORNAR UNA EDITORIAL*/
    public Editorial selectOneEditorial(int idE) throws Exception{
        return dao.selectEditorialByID(idE);
    }
    
}
