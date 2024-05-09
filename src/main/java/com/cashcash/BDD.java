package com.cashcash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cashcash.entities.Client;
import com.cashcash.entities.ContratMaintenance;

public class BDD {

    /* bdd hebergée */
    private static final String DB_URL = "jdbc:mysql://185.207.226.14:3306/CashCash";
    private static final String DB_USERNAME = "CashCash";
    private static final String DB_PASSWORD = "DT56gK_*p%-3ol2H";

    /* en local */
    // private static final String DB_URL = "jdbc:mysql://localhost:3306/cashcash";
    // private static final String DB_USERNAME = "root";
    // private static final String DB_PASSWORD = "";

    private Connection connexion;

    public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);		
            System.out.println("Connecté");
        } 
        catch (Exception e) {
			e.printStackTrace();
		}
		return connexion;
	}

}
