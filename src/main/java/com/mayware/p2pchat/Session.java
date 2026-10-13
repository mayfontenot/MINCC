package com.mayware.p2pchat;

import database.MyContactDao;
import entities.Contact;
import interfaces.DaoStrong;
import java.sql.SQLException;

/**
 *
 * @author azurith
 */
public class Session {
    private static final DaoStrong<Integer, Contact> myContactDao = new MyContactDao();
    private static Contact myContact;
    
    public static void initialize() {
        try {
            myContact = new Contact("Alias", "chat.example.com", myContactDao.select().get(0).getPublicKey());
        } catch (SQLException ex) {
            System.getLogger(Session.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
