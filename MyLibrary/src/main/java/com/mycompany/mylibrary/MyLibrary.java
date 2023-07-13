/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mylibrary;

import Services.*;



/**
 *
 * @author irina
 */
public class MyLibrary {

    public static void main(String[] args) throws Exception {
        Service srev = new Service();
        boolean flag;
        do {
            flag = srev.menuServ();
            System.out.printf("\n\n\n");
        } while (flag);
        
    }
}
