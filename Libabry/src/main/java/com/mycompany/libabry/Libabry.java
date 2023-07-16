/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.libabry;

import Services.Service;

/**
 *
 * @author irina
 */
public class Libabry {

    public static void main(String[] args) throws Exception {
        Service srev = new Service();
        boolean flag;
        do {
            flag = srev.menuServ();
            System.out.printf("\n\n\n");
        } while (flag);
    }
}
