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
public class EditorialService extends Printable {

    Scanner scaner = new Scanner(System.in);
    EditorialDAO dao;

    public EditorialService() {
        this.dao = new EditorialDAO();
    }

    //OPCION 2 DEL MENU
    public void insertEditorial() throws Exception {
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

                }
            }

        }

        if (!findEditorial(editorial)) {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|  EDITORIAL SUCCESSFULLY ADDED TO THE DATABASE   |");
            System.out.println("|-------------------------------------------------|");
            dao.insert(editorial);
        } else {
            System.out.println("|-------------------------------------------------|");
            System.out.println("|   EDITORIAL ALREDY EXISTS                       |");
            System.out.println("|-------------------------------------------------|");
        }
    }

    //OPCION 5 DEL MENU
    public void deleteEditorial() throws Exception {
        
        
        printOpc5();
        showEditorials();
        //PEDIR AL USUARIO
        System.out.print("   - SELECT EDITORIAL ID: ");
        
        //INSTANCIAMOS OBJETO DE TIPO EDITORIAL
        Editorial editorial = dao.selectEditorialByID(scaner.nextInt());
        
        if (findEditorial(editorial)) {
            dao.delete(editorial.getId());
            System.out.println("|-------------------------------------------------|");
            System.out.println("|EDITORIAL SUCCESSFULLY DELETED FROM THE DATABASE |");
            System.out.println("|-------------------------------------------------|");
        }else{
            System.out.println("|-------------------------------------------------|");
            System.out.println("| THE EDITORIAL DOES NOT EXIST, PLEASE TRY AGAIN  |");
            System.out.println("|-------------------------------------------------|");
        }

    }

    //IMPRIMIR EDITORIALES ---------------------------------------------------------
    public void showEditorials() throws Exception {
        List<Editorial> editoriales = dao.selectEditorial();
        String vID = "___ ID ___", vName = "________________ NAME ________________";
        System.out.println("|-------------------------------------------------|");
        System.out.println("|                    EDITORIALS                   |");
        System.out.println("|-------------------------------------------------|");
        System.out.println("|" + vID + "|" + vName + "|");
        for (Editorial aux : editoriales) {
            imprimirCasilla(String.valueOf(aux.getId()), vID);
            imprimirCasilla(aux.getName(), vName);
            System.out.println("|");
        }
        System.out.println("|-------------------------------------------------|");
    }

    //RETORNAR VALORES ---------------------------------------------------------
    /*RETORNAR UNA EDITORIAL*/
    public Editorial selectOneEditorial(int idE) throws Exception {
        return dao.selectEditorialByID(idE);
    }

    /*VERIFICAR QUE LA EDITORIAL EXISTA*/
    private boolean findEditorial(Editorial editorial) throws Exception {
        List<Editorial> editoriales = dao.selectEditorial();
        if (!editoriales.isEmpty()) {
            for (Editorial aux : editoriales) {
                if (aux.getId() == editorial.getId()) {
                    return true;
                }
            }
        }
        
        return false;
    }

}
