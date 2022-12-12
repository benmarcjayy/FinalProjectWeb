/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Benmarc Agulto
 */
public class DBUtil {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("FinalProjectWebPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
