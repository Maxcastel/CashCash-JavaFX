package com.cashcash;

import java.sql.Connection;
import java.sql.DriverManager;

public class Bdd {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cashcash";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    private Connection connexion;

    public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);		
            System.out.println("Connecté");
        } 
        catch (Exception e) {
			e.getMessage();
		}
		return connexion;
	}
}
